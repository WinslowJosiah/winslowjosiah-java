package winslow_assignment3;

import java.util.Arrays;

public class Chapter3 extends Chapter {
    public Chapter3() {
        super(
                "Decision Structures",
                Arrays.asList(4, 17, 18),
                Arrays.asList(
                        new Challenge3_4(),
                        new Challenge3_17(),
                        new Challenge3_18()
                )
        );
    }
}
