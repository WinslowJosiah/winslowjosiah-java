package sometime.ast;

import java.util.List;
import sometime.token.Token;

public abstract class OperationalAST extends ExpressionalAST {
    public Token op;
    public List<ExpressionalAST> params;
    
    public OperationalAST(Token op, List<ExpressionalAST> params) {
        this.op = op;
        this.params = params;
    }
}
