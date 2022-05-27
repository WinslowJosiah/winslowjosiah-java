package sometime.value;

import java.math.BigInteger;

public class NumValue extends Value<BigInteger> {
    public NumValue(BigInteger value) {
        super(value);
    }
    
    @Override
    public boolean equals(Object o) {
        // self check
        if (this == o) return true;
        // null check
        if (o == null) return false;
        // type check
        if (o instanceof BigInteger v) {
            return this.equals(new NumValue(v));
        }
        if (this.getClass() != o.getClass()) return false;
        // cast
        NumValue that = (NumValue) o;
        
        return this.value.equals(that.value);
    }
}
