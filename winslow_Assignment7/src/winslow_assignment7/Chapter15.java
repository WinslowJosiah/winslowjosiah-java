package winslow_assignment7;

import java.util.Arrays;

public class Chapter15 extends Chapter {
    public Chapter15() {
        super(
                "Recursion",
                Arrays.asList(1, 5, 9),
                Arrays.asList(
                        new Challenge15_1(),
                        new Challenge15_5(),
                        new Challenge15_9()
                )
        );
    }
}
