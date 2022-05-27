package sometime.interpreter;

import java.io.InputStream;
import java.io.PrintStream;

public class Environment {
    public InputStream in;
    public PrintStream out;
    public PrintStream err;
    
    public Environment(InputStream in, PrintStream out, PrintStream err) {
        this.in = in;
        this.out = out;
        this.err = err;
    }
    
    public Environment() {
        this(System.in, System.out, System.err);
    }
}
