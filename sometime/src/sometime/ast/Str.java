package sometime.ast;

import sometime.value.StrValue;

public class Str extends ValuationalAST {
    public Str(StrValue value) {
        this.value = value;
    }
}
