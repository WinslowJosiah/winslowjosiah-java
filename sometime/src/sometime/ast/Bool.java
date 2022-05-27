package sometime.ast;

import sometime.value.BoolValue;

public class Bool extends ValuationalAST {
    public Bool(BoolValue value) {
        this.value = value;
    }
}
