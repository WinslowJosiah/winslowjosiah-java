package sometime.ast;

import java.util.List;
import sometime.token.Token;

public class Function extends OperationalAST {
    // not much here
    public Function(Token op, List<ExpressionalAST> params) {
        super(op, params);
    }
}
