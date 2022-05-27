package sometime.token;

public enum TokenType {
    PLUS("+"),
    MINUS("-"),
    MUL("*"),
    DIV("/"),
    MOD("%"),
    NOT("!"),
    LPAREN("("),
    RPAREN(")"),
    LT("<"),
    GT(">"),
    SEMI(";"),
    COMMA(","),
    HASH("#"),
    NEWLINE("\n"),
    
    LEQ("<="),
    GEQ(">="),
    NEQ("!="),
    EQU("=="),
    AND("&&"),
    OR("||"),
    
    DEFER("defer", true),
    AGAIN("again", true),
    FORGET("forget", true),
    TRUE("true", true),
    FALSE("false", true),
    NFUNC("N", true),
    UFUNC("U", true),
    READ("read", true),
    PRINT("print", true),
    
    NUMBER("NUMBER"),
    BOOLEAN("BOOLEAN"),
    STRING("STRING"),
    EOF("EOF");
    
    private final String word;
    private final boolean reserved;
    
    private TokenType(final String word, final boolean reserved) {
        this.word = word;
        this.reserved = reserved;
    }
    
    private TokenType(final String word) {
        this(word, false);
    }
    
    public String getWord() {
        return word;
    }
    
    public boolean isReserved() {
        return reserved;
    }
}
