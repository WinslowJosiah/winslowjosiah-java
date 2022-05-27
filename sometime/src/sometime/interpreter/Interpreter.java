package sometime.interpreter;

import java.math.BigInteger;
import sometime.ast.*;
import sometime.exception.NodeVisitorException;
import sometime.exception.ToDoListException;
import sometime.value.*;

public class Interpreter extends NodeVisitor {
    private Program program;
    private Environment env;
    private int optLevel;
    private Evaluator eval;
    private ToDoList toDoList;
    private BigInteger currentLine;
    
    public Interpreter(Program program, Environment env, int optLevel)
            throws ToDoListException {
        this.program = program;
        this.env = env;
        this.optLevel = optLevel;
        this.toDoList = new ToDoList(this.program);
        this.eval = new Evaluator(this.toDoList, this.env, this.optLevel);
        
        this.currentLine = BigInteger.ZERO;
    }
    
    public Interpreter(Program program, Environment env)
            throws ToDoListException {
        this(program, env, 0);
    }
    
    public Interpreter(Program program, int optLevel) throws ToDoListException {
        this(program, new Environment(), optLevel);
    }
    
    public Interpreter(Program program) throws ToDoListException {
        this(program, new Environment(), 0);
    }
    
    public void execute() throws NoSuchMethodException, NodeVisitorException {
        while (toDoList.getTotalToDo().signum() > 0) {
            executeOneStep();
        }
    }
    
    private void executeOneStep()
            throws NoSuchMethodException, NodeVisitorException {
        BigInteger randomLine = toDoList.getRandomLine();
        if (randomLine == null) return;
        visit((AST) toDoList.getLine(randomLine).getNode());
    }
    
    private void checkNopLine(Line node) {
        if (node.isNop) return;
        
        for (ExpressionalAST deferNode : node.deferNodes) {
            Value simpleValue = deferNode.simpleValue;
            if (simpleValue == null) continue;
            
            if (simpleValue.equals(new BoolValue(true))
                    || simpleValue.equals(new NumValue(node.lineNumber))
            ) {
                node.isNop = true;
                return;
            }
        }
        
        for (ExpressionalAST againNode : node.againNodes) {
            Value simpleValue = againNode.simpleValue;
            if (simpleValue == null) continue;
            
            if (!simpleValue.equals(false)) return;
        }
        
        for (ExpressionalAST forgetNode : node.forgetNodes) {
            Value simpleValue = forgetNode.simpleValue;
            if (simpleValue == null) continue;
            
            if (!simpleValue.equals(false)) return;
        }
        
        if (node.nodes.size() > 1) return;
        
        StatementalAST firstStatement = (StatementalAST) node.nodes.get(0);
        if (!(firstStatement instanceof StatementPush)) return;
        
        StatementPush statement = (StatementPush) firstStatement;
        if (statement.lineNumber.simpleValue == null) return;
        BigInteger lineNumber =
                (BigInteger) statement.lineNumber.simpleValue.getValue();
        BigInteger copies = BigInteger.ONE;
        if (statement.copies != null) {
            if (statement.copies.simpleValue == null) return;
            copies = (BigInteger) statement.copies.simpleValue.getValue();
        }
        
        if (lineNumber.signum() < 0) {
            lineNumber = lineNumber.negate();
            copies = copies.negate();
        }
        
        if (!lineNumber.equals(node.lineNumber)
            || !copies.equals(BigInteger.ONE)
        ) {
            return;
        }
        
        node.isNop = true;
    }
    
    @Override
    Object genericVisit(AST node) throws NoSuchMethodException {
        try {
            if (node instanceof ExpressionalAST) {
                return eval.evaluate(node, currentLine);
            }
        } catch (IllegalArgumentException | NoSuchMethodException
                | NodeVisitorException e) {
            return super.genericVisit(node);
        }
        
        return null; // my IDE yells at me if this isn't here
    }
    
    Object visit_Line(AST node)
            throws NoSuchMethodException, NodeVisitorException {
        Line lineNode = (Line) node;
        
        currentLine = lineNode.lineNumber;
        
        if (optLevel >= 3) {
            checkNopLine(lineNode);
        }
        
        for (ExpressionalAST deferNode : lineNode.deferNodes) {
            if (eval.asBoolean((Value) visit(deferNode)).getValue()) {
                if (optLevel >= 2) {
                    toDoList.markDefer(currentLine);
                }
                return null;
            }
        }
        
        if (optLevel >= 2) {
            toDoList.unmarkDefers();
        }
        
        boolean forgetLine = false;
        for (ExpressionalAST forgetNode : lineNode.forgetNodes) {
            if (eval.asBoolean((Value) visit(forgetNode)).getValue()) {
                forgetLine = true;
                break;
            }
        }
        if (!forgetLine) {
            for (AST statement : lineNode.nodes) {
                visit(statement);
            }
            
            for (ExpressionalAST againNode : lineNode.againNodes) {
                if (eval.asBoolean((Value) visit(againNode)).getValue()) {
                    return null;
                }
            }
        }
        
        toDoList.changeCount(currentLine, BigInteger.valueOf(-1));
        return null;
    }
    
    Object visit_StatementPush(AST node)
            throws NoSuchMethodException, NodeVisitorException {
        StatementPush pushNode = (StatementPush) node;
        
        BigInteger lineNumber = (BigInteger) (
                (Value) visit(pushNode.lineNumber)
        ).getValue();
        BigInteger copies = BigInteger.ONE;
        if (pushNode.copies != null) {
            copies = (BigInteger) ((Value) visit(pushNode.copies)).getValue();
        }
        
        toDoList.changeCount(lineNumber, copies);
        
        return null;
    }
    
    Object visit_StatementPrint(AST node)
            throws NoSuchMethodException, NodeVisitorException {
        StatementPrint printNode = (StatementPrint) node;
        
        for (ExpressionalAST param : printNode.params) {
            Value res = (Value) visit(param);
            env.out.print(res.getValue());
            env.out.flush();
        }
        
        return null;
    }
}
