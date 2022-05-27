package sometime.ast;

import java.util.List;
import sometime.token.Token;

public class Operation extends OperationalAST {
    // not much here
    public Operation(Token op, List<ExpressionalAST> params) {
        super(op, params);
    }
}
