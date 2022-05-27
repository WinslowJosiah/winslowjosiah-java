package sometime.ast;

import sometime.value.NumValue;

public class Num extends ValuationalAST {
    public Num(NumValue value) {
        this.value = value;
    }
}
