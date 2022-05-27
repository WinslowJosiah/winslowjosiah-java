package winslow_assignment5;

import java.util.Arrays;

public class Chapter7 extends Chapter {
    public Chapter7() {
        super(
                "Arrays and the ArrayList Class",
                Arrays.asList(8, 15, 19),
                Arrays.asList(
                        new Challenge7_8(),
                        new Challenge7_15(),
                        new Challenge7_19()
                )
        );
    }
}
