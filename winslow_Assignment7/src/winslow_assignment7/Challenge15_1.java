package winslow_assignment7;

import java.util.Random;

public class Challenge15_1 extends Challenge {
    public Challenge15_1() {
        super("Recursive Multiplication");
    }
    
    /*
    Write a recursive function that accepts two arguments into the parameters x
    and y. The function should return the value of x times y. Remember,
    multiplication can be performed as repeated addition as follows:
    
        7 * 4 = 4 + 4 + 4 + 4 + 4 + 4 + 4
    */
    
    private final int trials = 20;
    private final int minNum = -10000;
    private final int maxNum = 10000;
    
    public void execute() {
        Random rand = new Random();
        
        for (int i = 0; i < trials; i++) {
            int argX = rand.nextInt(maxNum - minNum + 1) + minNum;
            int argY = rand.nextInt(maxNum - minNum + 1) + minNum;
            System.out.printf("%d * %d = %d\n",
                    argX, argY, multiply(argX, argY)
            );
        }
    }
    
    public int multiply(int x, int y) {
        if (x == 0 || y == 0) return 0;
        if (x < 0) return -multiply(-x, y);
        if (y < 0) return -multiply(x, -y);
        // recursively decrease the smaller number, for speed/stack reasons
        if (x > y) {
            return multiply(x, y - 1) + x;
        } else {
            return multiply(x - 1, y) + y;
        }
    }
}
