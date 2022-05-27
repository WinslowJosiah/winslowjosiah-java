package sometime.value;

public abstract class Value<T> {
    T value;
    
    public Value(T value) {
        setValue(value);
    }
    
    public void setValue(T value) {
        this.value = value;
    }
    
    public T getValue() {
        return value;
    }
}
