package sometime.ast;

import java.util.List;

public class StatementPrint extends StatementalAST {
    public List<ExpressionalAST> params;
    
    public StatementPrint(List<ExpressionalAST> params) {
        this.params = params;
    }
}
