package winslow_assignment7;

public class Challenge15_9 extends Challenge {
    public Challenge15_9() {
        super("Ackermann's Function");
    }
    
    /*
    Ackermann's function is a recursive mathematical algorithm that can be used
    to test how well a computer performs recursion. Write a method
    ackermann(m, n), which solves Ackermann's function. Use the following logic
    in your method:
    
        If m = 0 then return n + 1
        If n = 0 then return ackermann(m - 1, 1)
        Otherwise, return ackermann(m - 1, ackermann(m, n - 1))
    
    Test your method in a program that displays the return values of the
    following method calls:
    
        ackermann(0, 0)  ackermann(0, 1)  ackermann(1, 1)  ackermann(1, 2)
        ackermann(1, 3)  ackermann(2, 2)  ackermann(3, 2)
    */
    
    private final int[][] methodArgs = {
        {0, 0}, {0, 1}, {1, 1}, {1, 2},
        {1, 3}, {2, 2}, {3, 2},
    };
    
    public void execute() {
        for (int[] argsList : methodArgs) {
            int argM = argsList[0];
            int argN = argsList[1];
            System.out.printf("ackermann(%d, %d) = %d\n",
                    argM, argN, ackermann(argM, argN)
            );
        }
    }
    
    public int ackermann(int m, int n) {
        if (m == 0) return n + 1;
        if (n == 0) return ackermann(m - 1, 1);
        return ackermann(m - 1, ackermann(m, n - 1));
    }
}
