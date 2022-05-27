package winslow_assignment4;

import java.util.Arrays;

public class Chapter5 extends Chapter {
    public Chapter5() {
        super(
                "Methods",
                Arrays.asList(10, 16, 17),
                Arrays.asList(
                        new Challenge5_10(),
                        new Challenge5_16(),
                        new Challenge5_17()
                )
        );
    }
}
