package winslow_assignment5;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Challenge7_15 extends Challenge {
    public Challenge7_15() {
        super("Population Data");
    }
    
    /*
    If you have downloaded this book's source code (the companion Web site is
    available at www.pearsonhighered.com/gaddis), you will find a file named
    USPopulation.txt in the Chapter 07 folder. The file contains the midyear
    population of the United States, in thousands, during the years 1950 through
    1990. The first line in the file contains the population for 1950, the
    second line contains the population for 1951, and so forth.
    
    Write a program that reads the file's contents into an array. The program
    should display the following data:
    
    * The average annual change in population during the time period
    * The year with the greatest increase in population during the time period
    * The year with the smallest increate in population during the time period
    */
    
    private final String usPopFilename = "USPopulation.txt";
    
    public void execute() {
        List<Integer> usPop;
        try {
            usPop = readNumbersFromFile(usPopFilename);
        }
        catch (IOException e) {
            System.out.println("Couldn't find file!");
            return;
        }
        
        List<Integer> usPopDeltas = calculateDeltas(usPop);
        
        // We need the average, min, and max of the list all at once.
        // So why not calculate them together?
        
        double average = 0;
        // I don't really need to initialize these min/max ints here,
        // no matter how much Netbeans yells at me if I don't
        int minVal = 0;
        int minIndex = 0;
        int maxVal = 0;
        int maxIndex = 0;
        boolean minMaxInit = false;
        
        for (int i = 0; i < usPopDeltas.size(); i++) {
            int delta = usPopDeltas.get(i);
            
            // Update sum
            average += delta;
            
            // If this is the first iteration, init min and max without updating
            if (!minMaxInit) {
                minMaxInit = true;
                
                minVal = delta;
                minIndex = 0;
                maxVal = delta;
                maxIndex = 0;
                continue;
            }
            
            // Update min
            if (delta < minVal) {
                minVal = delta;
                minIndex = i;
            }
            
            // Update max
            if (delta > maxVal) {
                maxVal = delta;
                maxIndex = i;
            }
        }
        // Divide sum by size to get the average
        average /= (double) usPopDeltas.size();
        
        System.out.printf("Average annual change: %f people\n", average * 1000);
        System.out.printf("Year with largest increase: %d\n", 1951 + maxIndex);
        System.out.printf("Year with smallest increase: %d\n", 1951 + minIndex);
    }
    
    // Use Scanner to read int tokens from a file into a list
    private List<Integer> readNumbersFromFile(String path) throws IOException {
        List<Integer> nums = new ArrayList<>();
        
        File file = new File(path);
        Scanner fileIn = new Scanner(file);
        
        while (fileIn.hasNext()) {
            nums.add(fileIn.nextInt());
        }
        
        return nums;
    }
    
    // Calculate a list of changes between values in a given list
    private List<Integer> calculateDeltas(List<Integer> nums) {
        List<Integer> deltas = new ArrayList<>();
        
        for (int i = 0; i < nums.size() - 1; i++) {
            deltas.add(nums.get(i + 1) - nums.get(i));
        }
        
        return deltas;
    }
}
