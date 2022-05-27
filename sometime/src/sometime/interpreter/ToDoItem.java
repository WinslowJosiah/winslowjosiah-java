package sometime.interpreter;

import java.math.BigInteger;
import sometime.ast.Line;

public class ToDoItem {
    private final Line node;
    private BigInteger count;
    
    public ToDoItem(Line node) {
        this.node = node;
        this.count = BigInteger.ONE;
    }
    
    public Line getNode() {
        return node;
    }
    
    public BigInteger getCount() {
        return count;
    }
    
    public BigInteger changeCount(BigInteger change) {
        count = count.add(change);
        BigInteger extra = BigInteger.ZERO;
        if (count.signum() < 0) {
            extra = count.negate();
            count = BigInteger.ZERO;
        }
        return extra;
    }
}
