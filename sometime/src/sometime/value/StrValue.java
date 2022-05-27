package sometime.value;

public class StrValue extends Value<String> {
    public StrValue(String value) {
        super(value);
    }
    
    @Override
    public boolean equals(Object o) {
        // self check
        if (this == o) return true;
        // null check
        if (o == null) return false;
        // type check
        if (o instanceof String v) {
            return this.equals(new StrValue(v));
        }
        if (this.getClass() != o.getClass()) return false;
        // cast
        StrValue that = (StrValue) o;
        
        return this.value.equals(that.value);
    }
}
