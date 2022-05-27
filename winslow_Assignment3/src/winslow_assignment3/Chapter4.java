package winslow_assignment3;

import java.util.Arrays;

public class Chapter4 extends Chapter {
    public Chapter4() {
        super(
                "Loops and Files",
                Arrays.asList(8, 16, 22),
                Arrays.asList(
                        new Challenge4_8(),
                        new Challenge4_16(),
                        new Challenge4_22()
                )
        );
    }
}
