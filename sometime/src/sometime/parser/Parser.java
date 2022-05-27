package sometime.parser;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import sometime.ast.*;
import sometime.exception.LexerException;
import sometime.exception.ParserException;
import sometime.lexer.Lexer;
import sometime.token.Token;
import sometime.token.TokenType;
import sometime.value.*;

public class Parser {
    private final Lexer lexer;
    private Token currentToken;
    
    public Parser(Lexer lexer) throws Exception {
        this.lexer = lexer;
        eat();
    }
    
    public Program parse() throws Exception {
        return _program_();
    }
    
    private Token getNextToken() throws IOException, LexerException {
        return lexer.getNextToken();
    }
    
    private void eat() throws IOException, LexerException {
        currentToken = getNextToken();
    }
    
    private void eat(TokenType tType)
            throws IOException, LexerException, ParserException {
        if (currentToken.getType() != tType) {
            throw new ParserException(String.format(
                    "asked to eat token %s, saw token %s",
                    tType.name(), currentToken.getType().name()
            ));
        }
        eat();
    }
    
    private void eatChoice(TokenType... tTypes)
            throws IOException, LexerException, ParserException {
        for (TokenType tType : tTypes) {
            if (currentToken.getType() == tType) {
                eat();
                return;
            }
        }
        
        throw new ParserException(String.format(
                "asked to eat any of tokens [%s], saw token %s",
                String.join(", ",
                        Arrays.stream(tTypes).map(t -> t.name()).toList()
                ), currentToken.getType().name()
        ));
    }
    
    private Program _program_()
            throws IOException, LexerException, ParserException {
        List<AST> lines = new ArrayList();
        
        while (currentToken.getType() == TokenType.NEWLINE) {
            eat(TokenType.NEWLINE);
        }
        
        while (currentToken.getType() != TokenType.EOF) {
            lines.add(_line_());
            while (currentToken.getType() == TokenType.NEWLINE) {
                eat(TokenType.NEWLINE);
            }
        }
        
        return new Program(lines);
    }
    
    private Line _line_() throws IOException, LexerException, ParserException {
        Num lineNumberNode = _lineNumber_();
        BigInteger lineNumber = (BigInteger) lineNumberNode.value.getValue();
        
        List<ExpressionalAST> againNodes = new ArrayList();
        List<ExpressionalAST> deferNodes = new ArrayList();
        List<ExpressionalAST> forgetNodes = new ArrayList();
        while (
                currentToken.getType() == TokenType.AGAIN
                || currentToken.getType() == TokenType.DEFER
                || currentToken.getType() == TokenType.FORGET
        ) {
            Token condition = currentToken;
            eat();
            eat(TokenType.LPAREN);
            ExpressionalAST expr = _expr_();
            switch (condition.getType()) {
                case AGAIN -> againNodes.add(expr);
                case DEFER -> deferNodes.add(expr);
                case FORGET -> forgetNodes.add(expr);
            }
            eat(TokenType.RPAREN);
        }
        
        List<AST> nodes = _action_();
        
        eat(TokenType.SEMI);
        eatChoice(TokenType.NEWLINE, TokenType.EOF);
        
        return new Line(nodes, lineNumber, againNodes, deferNodes, forgetNodes);
    }
    
    private Num _lineNumber_()
            throws IOException, LexerException, ParserException {
        NumValue numValue = (NumValue) currentToken.getValue();
        if (numValue.getValue().signum() == 0) {
            throw new ParserException("line defined with line number 0");
        }
        eat(TokenType.NUMBER);
        return new Num(numValue);
    }
    
    private List<AST> _action_()
            throws IOException, LexerException, ParserException {
        List<AST> statements = new ArrayList();
        statements.add(_statement_());
        while (currentToken.getType() == TokenType.COMMA) {
            eat(TokenType.COMMA);
            statements.add(_statement_());
        }
        return statements;
    }
    
    private StatementalAST _statement_()
            throws IOException, LexerException, ParserException {
        return switch (currentToken.getType()) {
            case PRINT -> _statementPrint_();
            default -> _statementPush_();
        };
    }
    
    private StatementPush _statementPush_()
            throws IOException, LexerException, ParserException {
        ExpressionalAST left = _expr_();
        ExpressionalAST right = null;
        
        if (currentToken.getType() == TokenType.HASH) {
            eat(TokenType.HASH);
            right = _expr_();
        }
        
        return new StatementPush(left, right);
    }
    
    private StatementPrint _statementPrint_()
            throws IOException, LexerException, ParserException {
        eat(TokenType.PRINT);
        eat(TokenType.LPAREN);
        
        List<ExpressionalAST> params = new ArrayList();
        if (currentToken.getType() != TokenType.RPAREN) {
            params.add(_stringExpr_());
            
            while (currentToken.getType() == TokenType.COMMA) {
                eat(TokenType.COMMA);
                params.add(_stringExpr_());
            }
        }
        
        eat(TokenType.RPAREN);
        
        return new StatementPrint(params);
    }
    
    private Str _string_() throws IOException, LexerException, ParserException {
        StrValue strValue = (StrValue) currentToken.getValue();
        Str node = new Str(strValue);
        eat(TokenType.STRING);
        return node;
    }
    
    private ExpressionalAST _stringExpr_()
            throws IOException, LexerException, ParserException {
        return switch (currentToken.getType()) {
            case STRING -> _string_();
            default -> _expr_();
        };
    }
    
    private ExpressionalAST _expr_()
            throws IOException, LexerException, ParserException {
        return _logicalOr_();
    }
    
    private ExpressionalAST _logicalOr_()
            throws IOException, LexerException, ParserException{
        ExpressionalAST node = _logicalAnd_();
        
        while (
                currentToken.getType() == TokenType.OR
        ) {
                Token op = currentToken;
                eat();
                List<ExpressionalAST> params = new ArrayList();
                params.add(node);
                params.add(_logicalAnd_());
                node = new Operation(op, params);
        }
        
        return node;
    }
    
    private ExpressionalAST _logicalAnd_()
            throws IOException, LexerException, ParserException {
        ExpressionalAST node = _inequality_();
        
        while (
                currentToken.getType() == TokenType.AND
        ) {
                Token op = currentToken;
                eat();
                List<ExpressionalAST> params = new ArrayList();
                params.add(node);
                params.add(_inequality_());
                node = new Operation(op, params);
        }
        
        return node;
    }
    
    private ExpressionalAST _inequality_()
            throws IOException, LexerException, ParserException {
        ExpressionalAST node = _equality_();
        
        while (
                currentToken.getType() == TokenType.LT
                || currentToken.getType() == TokenType.GT
                || currentToken.getType() == TokenType.LEQ
                || currentToken.getType() == TokenType.GEQ
        ) {
                Token op = currentToken;
                eat();
                List<ExpressionalAST> params = new ArrayList();
                params.add(node);
                params.add(_equality_());
                node = new Operation(op, params);
        }
        
        return node;
    }
    
    private ExpressionalAST _equality_()
            throws IOException, LexerException, ParserException {
        ExpressionalAST node = _sum_();
        
        while (
                currentToken.getType() == TokenType.EQU
                || currentToken.getType() == TokenType.NEQ
        ) {
                Token op = currentToken;
                eat();
                List<ExpressionalAST> params = new ArrayList();
                params.add(node);
                params.add(_sum_());
                node = new Operation(op, params);
        }
        
        return node;
    }
    
    private ExpressionalAST _sum_()
            throws IOException, LexerException, ParserException {
        ExpressionalAST node = _term_();
        
        while (
                currentToken.getType() == TokenType.PLUS
                || currentToken.getType() == TokenType.MINUS
        ) {
                Token op = currentToken;
                eat();
                List<ExpressionalAST> params = new ArrayList();
                params.add(node);
                params.add(_term_());
                node = new Operation(op, params);
        }
        
        return node;
    }
    
    private ExpressionalAST _term_()
            throws IOException, LexerException, ParserException {
        ExpressionalAST node = _factor_();
        
        while (
                currentToken.getType() == TokenType.MUL
                || currentToken.getType() == TokenType.DIV
                || currentToken.getType() == TokenType.MOD
        ) {
                Token op = currentToken;
                eat();
                List<ExpressionalAST> params = new ArrayList();
                params.add(node);
                params.add(_factor_());
                node = new Operation(op, params);
        }
        
        return node;
    }
    
    private ExpressionalAST _factor_()
            throws IOException, LexerException, ParserException {
        Token token = currentToken;
        switch (token.getType()) {
            case NOT, PLUS, MINUS -> {
                eat();
                List<ExpressionalAST> params = new ArrayList();
                params.add(_factor_());
                return new Operation(token, params);
            }
            case NUMBER -> {
                eat();
                return new Num((NumValue) token.getValue());
            }
            case TRUE, FALSE, BOOLEAN -> {
                eat();
                return new Bool((BoolValue) token.getValue());
            }
            case LPAREN -> {
                eat(TokenType.LPAREN);
                ExpressionalAST node = _expr_();
                eat(TokenType.RPAREN);
                return node;
            }
            case NFUNC, UFUNC, READ -> {
                return _functionCall_();
            }
            default -> {
                throw new ParserException("unrecognized token");
            }
        }
    }
    
    private ExpressionalAST _functionCall_()
            throws IOException, LexerException, ParserException {
        Token token = currentToken;
        
        eat();
        eat(TokenType.LPAREN);
        
        List<ExpressionalAST> params = new ArrayList();
        if (currentToken.getType() != TokenType.RPAREN) {
            params.add(_expr_());
        }
        
        while (currentToken.getType() == TokenType.COMMA) {
            eat(TokenType.COMMA);
            params.add(_expr_());
        }
        
        eat(TokenType.RPAREN);
        
        return new Function(token, params);
    }
}
