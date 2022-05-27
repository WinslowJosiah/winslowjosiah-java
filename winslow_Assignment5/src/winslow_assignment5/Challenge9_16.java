package winslow_assignment5;

import java.util.Scanner;

public class Challenge9_16 extends Challenge {
    public Challenge9_16() {
        super("Morse Code Converter");
    }
    
    /*
    Morse code is a code where each letter of the English alphabet, each digit,
    and various punctuation characters are represented by a series of dots and
    dashes. Table 9-14 shows part of the code. Write a program that asks the
    user to enter a string, and then converts that string to Morse code. Use
    hyphens for dashes and periods for dots.
    
    Table 9-14: Morse code
    
     Char |  Code  | Char |  Code | Char | Code | Char | Code
    ------+--------+------+-------+------+------+------+------
    space |  space |   6  | -.... |   G  |  --. |   Q  | --.-
       ,  | --..-- |   7  | --... |   H  | .... |   R  |  .-.
       .  | .-.-.- |   8  | ---.. |   I  |  ..  |   S  |  ...
       ?  | ..--.. |   9  | ----. |   J  | .--- |   T  |   -
       0  |  ----- |   A  |  .-   |   K  |  -.- |   U  |  ..-
       1  |  .---- |   B  | -...  |   L  | .-.. |   V  | ...-
       2  |  ..--- |   C  | -.-.  |   M  |  --  |   W  |  .--
       3  |  ...-- |   D  |  -..  |   N  |  -.  |   X  | -..-
       4  |  ....- |   E  |   .   |   O  |  --- |   Y  | -.--
       5  |  ..... |   F  | ..-.  |   P  | .--. |   Z  | --..
    */
    
    public void execute() {
        Scanner in = new Scanner(System.in);
        
        System.out.print("Enter string: ");
        String str = in.nextLine();
        
        // StringBuilders are more efficient to append to than Strings,
        // because a new object isn't built each time
        StringBuilder morse = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            morse.append(charToMorse(str.charAt(i)));
            // Delimit by spaces
            if (i < str.length() - 1) {
                morse.append(' ');
            }
        }
        
        System.out.println(morse.toString());
    }
    
    private String charToMorse(char c) {
        // Handle space
        if (c == ' ') return " ";
        
        // This string is specially constructed such that, if you convert the
        // index of a char to binary (and get rid of the initial 1 digit), the
        // result will be the correct Morse code translation for that char.
        // (0 = dot, 1 = dash)
        // I could make this string contain every single char in the table,
        // but that would take more chars (most of which would be unused).
        int letterI = "  ETIANMSURWDKGOHVF L PJBXCYZQ"
                .indexOf(Character.toUpperCase(c));
        // If we found our letter in the string
        if (letterI >= 0) {
            // Do the binary magic
            String binCode = Integer.toBinaryString(letterI).substring(1);
            return binCode.replace('0', '.').replace('1', '-');
        }
        
        // Handle numbers (using a pattern in the Morse code!)
        int digit = c - '0'; // Chars are integers in disguise!
        // If this is a digit character
        if (digit >= 0 && digit <= 9) {
            // Special index into a special string
            int dotDashI = 9 - digit;
            return "----.....-----".substring(dotDashI, dotDashI + 5);
        }
        
        // Handle punctuation
        // This just uses a switch statement
        switch (c) {
            case '?':
                return "..--..";
            case ',':
                return "--..--";
            case '.':
                return ".-.-.-";
            // If all else fails, return something unknown
            default:
                return "?";
        }
    }
}
