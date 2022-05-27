package sometime.ast;

import java.math.BigInteger;
import java.util.List;

public class Line extends StructuralAST {
    public BigInteger lineNumber;
    public List<ExpressionalAST> deferNodes;
    public List<ExpressionalAST> againNodes;
    public List<ExpressionalAST> forgetNodes;
    public boolean isNop;
    
    public Line(
            List<AST> nodes, BigInteger lineNumber,
            List<ExpressionalAST> againNodes,
            List<ExpressionalAST> deferNodes,
            List<ExpressionalAST> forgetNodes
    ) {
        this.nodes = nodes;
        this.lineNumber = lineNumber;
        this.againNodes = againNodes;
        this.deferNodes = deferNodes;
        this.forgetNodes = forgetNodes;
        this.isNop = false;
    }
}
