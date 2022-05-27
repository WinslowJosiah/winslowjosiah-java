package sometime.value;

public class BoolValue extends Value<Boolean> {
    public BoolValue(boolean value) {
        super(value);
    }
    
    @Override
    public boolean equals(Object o) {
        // self check
        if (this == o) return true;
        // null check
        if (o == null) return false;
        // type check
        if (o instanceof Boolean v) {
            return this.equals(new BoolValue(v));
        }
        if (this.getClass() != o.getClass()) return false;
        // cast
        BoolValue that = (BoolValue) o;
        
        return this.value.equals(that.value);
    }
}
