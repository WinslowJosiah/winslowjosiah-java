package winslow_assignment5;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.Scanner;
import static winslow_assignment5.Winslow_Assignment5.inputChoiceChar;

public class Challenge7_19 extends Challenge {
    public Challenge7_19() {
        super("Trivia Game");
    }
    
    /*
    In this programming challenge, you will create a simple trivia game for two
    players. The program will work like this:
    
    * Starting with player 1, each player gets a turn at answering 5 trivia
    questions. (There are 10 questions, 5 for each player.) When a question is
    displayed, four possible answers are also displayed. Only one of the answers
    is correct, and if the player selects the correct answer, he or she earns a
    point.
    * After answers have been selected for all of the questions, the program
    displays the number of points earned by each player and declares the player
    with the highest number of points the winner.
    
    You are to design a Question class to hold the data for a trivia question.
    The Question class should have String fields for the following data:
    
    * A trivia question
    * Possible answer 1
    * Possible answer 2
    * Possible answer 3
    * Possible answer 4
    * The number of the correct answer (1, 2, 3, or 4)
    
    The Question class should have appropriate constructor(s), accessor, and
    mutator methods.
    
    The program should create an array of 10 Question objects, one for each
    trivia question. (If you prefer, you can use an ArrayList instead of an
    array.) Make up your own trivia questions on the subject or subjects of your
    choice for the objects.
    */
    
    private final String[] triviaFiles = new String[] {
        "bbtrivia.sb3", "bctrivia.sb3", "cbtrivia.sb3", "dgtrivia.sb3",
        "kftrivia.sb3"
    };
    private final String[] triviaTopics = new String[] {
        "Patrick", "Squidward", "Sandy", "Mr. Krabs", "Gary"
    };
    
    private final int answersPerQuestion = 4;
    private final int questionsPerPlayer = 5;
    private final int numberOfPlayers = 2;
    
    public void execute() {
        Random rand = new Random();
        Scanner in = new Scanner(System.in);
        
        // Get choice of trivia topic from the user
        System.out.println("Trivia topics:");
        for (int i = 0; i < triviaTopics.length; i++) {
            System.out.printf("%d. %s\n", i + 1, triviaTopics[i]);
        }
        
        int game;
        String gameInput;
        do {
            System.out.print("Enter topic: ");
            gameInput = in.nextLine();
            try {
                game = Integer.parseInt(gameInput);
            }
            catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
                continue;
            }
            
            if (game >= 1 && game <= triviaTopics.length) {
                game--; // use 0-indexing instead of 1-indexing!
                break;
            }
            
            System.out.println("Invalid topic!");
        } while (true);
        
        // Load lines
        Path triviaPath = Paths.get(triviaFiles[game]);
        
        String[] fileLines;
        try {
            fileLines = loadSbFileLines(triviaPath.toString());
        }
        catch (EOFException e) {
            System.out.println("Trivia file has been improperly encoded!");
            return;
        }
        catch (IOException e) {
            System.out.println("Trivia file not found!");
            return;
        }
        
        // Load questions from lines
        List<Question> questions = new ArrayList<>();
        try {
            int i = 0;
            while (i < fileLines.length) {
                String currQuestion = fileLines[i];
                i++;
                String[] currAnswers = new String[answersPerQuestion];
                for (int j = 0; j < answersPerQuestion; j++) {
                    currAnswers[j] = fileLines[i];
                    i++;
                }
                char currCorrectChar = fileLines[i].charAt(0);
                int currCorrect = currCorrectChar - 'A';
                // Throw an exception if we don't have a valid correct answer
                if (currCorrect < 0 || currCorrect >= answersPerQuestion) {
                    throw new RuntimeException(String.format(
                            "Invalid format for answer %d", i
                    ));
                }
                i++;
                
                questions.add(new Question(
                        currQuestion, currAnswers, currCorrect
                ));
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Trivia file has been improperly formatted!");
            return;
        }
        catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return;
        }
        
        // Shuffle the list of questions, Fisher-Yates style
        for (int i = questions.size() - 1; i > 0; i--) {
            int j = rand.nextInt(i);
            Question temp = questions.get(i);
            questions.set(i, questions.get(j));
            questions.set(j, temp);
        }
        
        // Gather the questions for each player
        // (We're assuming that there are enough questions here.)
        // (Normally I would check, but in this case, there definitely are.)
        Question[][] playerQuestions =
                new Question[numberOfPlayers][questionsPerPlayer];
        ListIterator qIter = questions.listIterator();
        for (int i = 0; i < numberOfPlayers; i++) {
            for (int j = 0; j < questionsPerPlayer; j++) {
                playerQuestions[i][j] = (Question) qIter.next();
            }
        }
        
        // Play the trivia game
        
        int[] playerPoints = new int[numberOfPlayers];
        // We don't technically have to fill this with 0, but I'll do it anyways
        Arrays.fill(playerPoints, 0);
        
        // Loop through each player
        for (int i = 0; i < playerQuestions.length; i++) {
            Question[] p = playerQuestions[i];
            
            System.out.printf("Player %d's turn.\n\n", i + 1);
            
            // Loop through all the questions
            for (Question q : p) {
                System.out.println(q.getQuestion());
                
                // Display all the answers
                String[] a = q.getAnswers();
                String answerChoices = "";
                for (int j = 0; j < a.length; j++) {
                    char answerChoice = (char) ('A' + j);
                    answerChoices += answerChoice;
                    System.out.printf("%s. %s\n", answerChoice, a[j]);
                }
                
                // Remember: this will return a lowercase character!
                char answerInput = inputChoiceChar(
                        answerChoices, "Enter your answer: ", "Invalid answer!"
                );
                
                // If we got the answer right
                if (answerInput - 'a' == q.getCorrectAnswer()) {
                    System.out.println("Correct!");
                    // Add to the current player's score
                    playerPoints[i]++;
                // If we got the answer wrong
                } else {
                    System.out.println("Incorrect...");
                    // Do nothing
                }
                
                System.out.println("");
            }
        }
        
        // Figure out who got the highest score
        List<Integer> best = new ArrayList<>();
        int maxScore = 0;
        for (int i = 0; i < playerPoints.length; i++) {
            int score = playerPoints[i];
            System.out.printf("Player %d's score: %d point%s\n",
                    i + 1, score,
                    score == 1 ? "" : "s"
            );
            
            if (score > maxScore) {
                maxScore = score;
                best.clear();
                best.add(i + 1);
            } else if (score == maxScore) {
                best.add(i + 1);
            }
        }
        
        // If only one player got the highest score
        if (best.size() == 1) {
            // That player wins
            System.out.printf("Player %d wins!\n", best.get(0));
        // If multiple players got the highest score
        } else {
            // Convert the list to a comma-separated English string
            List<String> bestStrings = new ArrayList<>();
            for (int i = 0; i < best.size(); i++) {
                bestStrings.add(best.get(i).toString());
            }
            String lastBest = bestStrings.remove(
                    bestStrings.size() - 1
            );
            bestStrings.set(bestStrings.size() - 1,
                    bestStrings.get(bestStrings.size() - 1) + " and " + lastBest
            );
            
            // It's a tie
            System.out.printf("It's a tie between players %s.\n",
                    String.join(", ", bestStrings)
            );
        }
    }
    
    private String[] loadSbFileLines(String path)
            throws IOException, EOFException {
        // The .sb3 files have this format:
        // a short (two bytes) for the number of lines,
        // then for each line, a short (two bytes) for the length of the line,
        // and then the line's contents.
        // (Each byte of the contents is XORed with 0x30, for obfuscation).
        
        FileInputStream file = new FileInputStream(path);
        DataInputStream data = new DataInputStream(file);
        
        byte[] shortBuffer = new byte[2];
        
        // Read the number of lines
        // There is a readShort() function in DataInputStream,
        // but that works with big-endian shorts, and these are little-endian
        data.readFully(shortBuffer);
        short numberOfLines = ByteBuffer.wrap(shortBuffer)
                .order(ByteOrder.LITTLE_ENDIAN)
                .getShort();
        String[] result = new String[numberOfLines];
        
        for (int i = 0; i < numberOfLines; i++) {
            // Read the length of this line
            // (Again, making sure it's little-endian)
            // (In any other situation, I might make this part a function)
            data.readFully(shortBuffer);
            short lineLength = ByteBuffer.wrap(shortBuffer)
                    .order(ByteOrder.LITTLE_ENDIAN)
                    .getShort();
            
            // Read the contents of this line
            byte[] lineBytes = new byte[lineLength];
            data.readFully(lineBytes);
            
            // De-obfuscate this line
            for (int j = 0; j < lineBytes.length; j++) {
                lineBytes[j] ^= 0x30;
            }
            
            // Add this line as a String to our results
            result[i] = new String(
                    lineBytes, Charset.forName("Cp1252")
            ).trim(); // trim the newline at the end
        }
        
        // Gotta remember to close the streams!
        // (This also closes the FileInputStream.)
        data.close();
        
        return result;
    }
}
