package winslow_assignment3;

import static winslow_assignment3.Winslow_Assignment3.inputChoiceChar;

public class Challenge3_17 extends Challenge {
    public Challenge3_17() {
        super("Wi-Fi Diagnostic Tree");
    }
    
    /*
    Figure 3-23 shows a simplified flowchart for troubleshooting a bad Wi-Fi
    connection. Use the flowchart to create a program that leads a person
    through the steps of fixing a bad Wi-Fi connection. Here is an example of
    the program's output:
    
        Reboot the computer and try to connect.
        Did that fix the problem?  no [Enter]
        Reboot the router and try to connect.
        Did that fix the problem?  yes [Enter]
    
    Notice that the program ends as soon as a solution is found to the problem.
    Here is another example of the program's output:
    
        Reboot the computer and try to connect.
        Did that fix the problem?  no [Enter]
        Reboot the router and try to connect.
        Did that fix the problem?  no [Enter]
        Make sure the cables between the router & modem are plugged in firmly.
        Did that fix the problem?  no [Enter]
        Move the router to a new location.
        Did that fix the problem?  no [Enter]
        Get a new router.
    */
    
    private final String[] tsSteps = {
        "Reboot the computer and try to connect.",
        "Reboot the router and try to connect.",
        "Make sure the cables between the router & modem are plugged in firmly.",
        "Move the router to a new location."
    };
    private final String tsLastStep = "Get a new router.";
    
    public void execute() {
        char problemFixedChar;
        boolean problemFixed = false;
        for (String ts: tsSteps) {
            // Print the next troubleshooting step
            System.out.println(ts);
            // I knew this function would come in handy
            problemFixedChar = inputChoiceChar(
                    "yn",
                    "Did that fix the problem?  ",
                    "Enter Y or N."
            );
            // If the problem was fixed
            if (problemFixedChar == 'y') {
                // Then it was fixed
                problemFixed = true;
                // We're done!
                break;
            }
        }
        
        // If the problem wasn't fixed
        if (!problemFixed) {
            // Give up
            System.out.println(tsLastStep);
        }
    }
}
