package sometime.ast;

import java.util.List;

public class Program extends StructuralAST {
    public Program(List<AST> nodes) {
        this.nodes = nodes;
    }
}
