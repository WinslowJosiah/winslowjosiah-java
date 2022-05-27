package sometime.interpreter;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import sometime.ast.*;
import sometime.exception.ToDoListException;

public class ToDoList {
    private final Random rnd;
    
    // I was told not to use BigIntegers as keys directly,
    // so instead, I'm using their hashcodes.
    // hopefully this works well enough
    private final HashMap<Integer, ToDoItem> list;
    private final HashMap<Integer, BigInteger> hashCodes;
    private BigInteger totalToDo;
    private BigInteger totalNops;
    private BigInteger totalDefers;
    private final HashSet<Integer> nops;
    private final HashSet<Integer> defers;
    
    public ToDoList(Program program) throws ToDoListException {
        this.rnd = new Random();
        
        this.list = new HashMap();
        this.hashCodes = new HashMap();
        this.totalToDo = BigInteger.valueOf(program.nodes.size());
        this.totalNops = BigInteger.ZERO;
        this.totalDefers = BigInteger.ZERO;
        this.nops = new HashSet();
        this.defers = new HashSet();
        
        for (AST line : program.nodes) {
            BigInteger lineNumber = ((Line) line).lineNumber;
            if (list.containsKey(lineNumber.hashCode())) {
                throw new ToDoListException("Duplicate line number");
            }
            list.put(lineNumber.hashCode(), new ToDoItem((Line) line));
            hashCodes.put(lineNumber.hashCode(), lineNumber);
        }
    }
    
    public BigInteger getTotalToDo() {
        return totalToDo;
    }
    
    public ToDoItem getLine(BigInteger lineNum) {
        if (list.containsKey(lineNum.hashCode())) {
            return list.get(lineNum.hashCode());
        }
        return null;
    }
    
    public BigInteger getLineCount(BigInteger lineNum) {
        ToDoItem line = getLine(lineNum);
        if (line == null) return BigInteger.ZERO;
        return line.getCount();
    }
    
    public void markDefer(BigInteger lineNum) {
        totalDefers = totalDefers.add(getLine(lineNum).getCount());
        defers.add(lineNum.hashCode());
    }
    
    public void unmarkDefers() {
        for (Integer lineNumHash : defers) {
            BigInteger lineNum = hashCodes.get(lineNumHash);
            totalDefers = totalDefers.subtract(getLine(lineNum).getCount());
        }
        defers.clear();
    }
    
    public void markNop(BigInteger lineNum) {
        totalNops = totalNops.add(getLine(lineNum).getCount());
        nops.add(lineNum.hashCode());
    }
    
    private BigInteger getRandomBigInteger(BigInteger end) {
        BigInteger res;
        do {
            res = new BigInteger(end.bitLength(), rnd);
        } while (res.compareTo(end) >= 0);
        return res;
    }
    
    public BigInteger getRandomLine() {
        BigInteger range = totalToDo.subtract(
                totalDefers.add(totalNops)
        );
        if (range.signum() <= 0) return null;
        BigInteger lineToDo = getRandomBigInteger(range);
        
        for (Map.Entry map : list.entrySet()) {
            Integer lineNumberHash = (Integer) map.getKey();
            ToDoItem item = (ToDoItem) map.getValue();
            
            if (defers.contains(lineNumberHash)) continue;
            if (nops.contains(lineNumberHash)) continue;
            
            if (item.getNode().isNop) {
                markNop(hashCodes.get(lineNumberHash));
            }
            
            if (lineToDo.compareTo(item.getCount()) < 0) {
                return hashCodes.get(lineNumberHash);
            }
            
            lineToDo = lineToDo.subtract(item.getCount());
        }
        
        throw new IndexOutOfBoundsException("Random line could not be chosen");
    }
    
    public void changeCount(BigInteger lineNumber, BigInteger copies) {
        if (lineNumber.signum() < 0) {
            lineNumber = lineNumber.negate();
            copies = copies.negate();
        }
        
        ToDoItem item = getLine(lineNumber);
        if (item == null) return;
        
        if (item.getCount().compareTo(copies.negate()) < 0) {
            copies = item.getCount().negate();
        }
        
        BigInteger extra = item.changeCount(copies);
        copies = copies.subtract(extra);
        
        totalToDo = totalToDo.add(copies);
        if (defers.contains(lineNumber.hashCode())) {
            totalDefers = totalDefers.add(copies);
        }
        if (nops.contains(lineNumber.hashCode())) {
            totalNops = totalNops.add(copies);
        }
    }
}
