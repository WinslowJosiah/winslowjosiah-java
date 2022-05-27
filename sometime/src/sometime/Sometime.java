package sometime;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import org.apache.commons.cli.*;
import sometime.exception.CommandLineException;
import sometime.interpreter.Interpreter;
import sometime.lexer.Lexer;
import sometime.parser.Parser;

public class Sometime {
    private static final String APP_NAME = "sometime [file]";
    private static final int MAX_OPT_LVL = 3;
    
    private static Options options;

    public static void main(String[] args) {
        options = new Options();
        options.addOption(Option.builder("o")
                .longOpt("optimize")
                .hasArg()
                .type(Number.class)
                .desc(String.format(
                        "optimization level, from 0 (off) to %d (max) "
                        + "(default: off)", MAX_OPT_LVL)
                )
                .build()
        );
        options.addOption(Option.builder("h")
                .longOpt("help")
                .desc("display this help message")
                .build()
        );
        
        CommandLineParser cliParser = new DefaultParser();
        CommandLine cliLine;
        try {
            cliLine = cliParser.parse(options, args);
        } catch (ParseException e) {
            printCmdLineError(e, "Options parsing");
            return;
        }
        
        if (cliLine.hasOption("help")) {
            printHelp();
            return;
        }
        
        int optLevel = 0;
        if (cliLine.hasOption("optimize")) {
            try {
                optLevel = ((Number) cliLine.getParsedOptionValue("optimize"))
                        .intValue();
                if (optLevel < 0 || optLevel > MAX_OPT_LVL) {
                    throw new CommandLineException(String.format(
                            "Optimization level outside of range 0..%d",
                            MAX_OPT_LVL
                    ));
                }
            } catch (ParseException | CommandLineException e) {
                printCmdLineError(e, "-o option parsing");
                return;
            }
        }
        
        Reader fReader;
        
        String[] lineArgs = cliLine.getArgs();
        try {
            if (lineArgs.length < 1) {
                throw new CommandLineException("filename not specified");
            }
            
            fReader = new FileReader(lineArgs[0]);
        } catch (FileNotFoundException | CommandLineException e) {
            printCmdLineError(e, "File opening");
            return;
        }
        
        try {
            Lexer lexer = new Lexer(fReader);
            Parser parser = new Parser(lexer);
            Interpreter interpreter = new Interpreter(parser.parse(), optLevel);
            interpreter.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void printCmdLineError(Exception e, String action) {
        System.err.println(action + " failed.");
        System.err.println("Reason: " + e.getMessage());
        printHelp();
    }
    
    private static void printHelp() {
        HelpFormatter cliHelp = new HelpFormatter();
        cliHelp.printHelp(APP_NAME, options, true);
    }
}
