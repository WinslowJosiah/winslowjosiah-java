package winslow_assignment7;

public class Challenge15_5 extends Challenge {
    public Challenge15_5() {
        super("Palindrome Detector");
    }
    
    /*
    A palindrome is any word, phrase, or sentence that reads the same forward
    and backward. Here are some well-known palindromes:
    
        Able was I, ere I saw Elba
        A man, a plan, a canal, Panama
        Desserts, I stressed
        Kayak
    
    Write a boolean method that uses recursion to determine whether a String
    argument is a palindrome. The method should return true if the argument
    reads the same forward and backward. Demonstrate the method in a program.
    */
    
    private final String[] palindromes = {
        "able was I ere I saw elba",  // true
        "AMANAPLANACANALPANAMA",      // true
        "dessertsistressed",          // true
        "kayak",                      // true
        "Able was I, ere I saw Elba", // false
        "FOOLPROOF",                  // false
        "eat at Joe's",               // false
        "the Fortnite cube, Kevin",   // false
    };
    
    public void execute() {
        for (String s : palindromes) {
            System.out.println(s);
            System.out.println(isPalindrome(s) ?
                    "This phrase is a palindrome!"
                    : "This phrase is not a palindrome..."
            );
        }
    }
    
    public boolean isPalindrome(String s) {
        return isPalindrome(s, 0, s.length() - 1);
    }
    
    public boolean isPalindrome(String s, int i, int j) {
        if (i >= j) return true;
        if (s.charAt(i) != s.charAt(j)) return false;
        return isPalindrome(s, i + 1, j - 1);
    }
}
