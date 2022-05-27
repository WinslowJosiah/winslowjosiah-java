package sometime.ast;

public class StatementPush extends StatementalAST {
    public ExpressionalAST lineNumber;
    public ExpressionalAST copies;
    
    public StatementPush(ExpressionalAST lineNumber, ExpressionalAST copies) {
        this.lineNumber = lineNumber;
        this.copies = copies;
    }
}
