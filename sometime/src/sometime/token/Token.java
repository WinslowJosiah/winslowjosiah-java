package sometime.token;

import sometime.value.Value;

public class Token {
    private TokenType type;
    private Value value;
    
    public Token(TokenType type, Value value) {
        setType(type);
        setValue(value);
    }
    
    public TokenType getType() {
        return type;
    }
    
    public Value getValue() {
        return value;
    }
    
    public void setType(TokenType type) {
        this.type = type;
    }
    
    public void setValue(Value value) {
        this.value = value;
    }
}
