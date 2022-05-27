package winslow_assignment6;

import java.util.Arrays;

public class Chapter10 extends Chapter {
    public Chapter10() {
        super(
                "Inheritance",
                Arrays.asList(1, 2, 3),
                Arrays.asList(
                        new Challenge10_1(),
                        new Challenge10_2(),
                        new Challenge10_3()
                )
        );
    }
}
