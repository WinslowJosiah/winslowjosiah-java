package sometime.lexer;

import java.io.IOException;
import java.io.PushbackReader;
import java.io.Reader;
import java.math.BigInteger;
import java.util.HashMap;
import sometime.exception.LexerException;
import sometime.token.Token;
import sometime.token.TokenType;
import sometime.value.*;

public class Lexer {
    private final PushbackReader pbReader;
    private Character currentChar; // must be nullable
    private final HashMap<String, TokenType> tokenTypes;
    private boolean doneReading;
    
    public Lexer(Reader reader) throws IOException {
        this.pbReader = new PushbackReader(reader);
        this.doneReading = false;
        advance();
        
        tokenTypes = new HashMap();
        for (TokenType tt : TokenType.values()) {
            tokenTypes.put(tt.getWord(), tt);
        }
    }
    
    private void advance() throws IOException {
        if (doneReading) {
            currentChar = null;
            return;
        }
        
        int next = pbReader.read();
        if (next < 0) {
            currentChar = null;
            doneReading = true;
        } else {
            currentChar = (char) next;
        }
    }
    
    private Character peek() throws IOException {
        if (doneReading) return null;
        
        int next = pbReader.read();
        if (next < 0) {
            doneReading = true;
            return null;
        } else {
            pbReader.unread(next);
            return (char) next;
        }
    }
    
    private Token _number_() throws IOException {
        StringBuilder res = new StringBuilder();
        while (currentChar != null && "0123456789".indexOf(currentChar) >= 0) {
            res.append(currentChar);
            advance();
        }
        
        return new Token(
                TokenType.NUMBER,
                new NumValue(new BigInteger(res.toString()))
        );
    }
    
    private Token _string_() throws IOException, LexerException {
        StringBuilder res = new StringBuilder();
        while (currentChar != null && currentChar != '"') {
            if (currentChar == '\\') {
                advance();
                int escapeI = "abfnrtv\\\"".indexOf(currentChar);
                if (escapeI >= 0) {
                    res.append("\u0007\b\f\n\r\t\u000B\\\""
                            .charAt(escapeI)
                    );
                } else {
                    res.append(currentChar);
                }
            } else if ("\r\n".indexOf(currentChar) >= 0) {
                throw new LexerException("newline inside string");
            } else {
                res.append(currentChar);
            }
            advance();
        }
        
        advance();
        return new Token(
                TokenType.STRING,
                new StrValue(res.toString())
        );
    }
    
    private Token _word_() throws IOException, LexerException {
        StringBuilder res = new StringBuilder();
        while (currentChar != null && Character.isAlphabetic(currentChar)) {
            res.append(currentChar);
            advance();
        }
        
        if (!tokenTypes.containsKey(res.toString())) {
            throw new LexerException(String.format(
                    "token for word %s was not found", res.toString()
            ));
        }
        
        TokenType tType = tokenTypes.get(res.toString());
        if (!tType.isReserved()) {
            throw new LexerException(String.format(
                    "token %s is not reserved", tType.name()
            ));
        }
        
        return switch (tType) {
            case TRUE -> new Token(
                    TokenType.BOOLEAN,
                    new BoolValue(true)
            );
            case FALSE -> new Token(
                    TokenType.BOOLEAN,
                    new BoolValue(false)
            );
            default -> new Token(
                    tType,
                    null
            );
        };
    }
    
    private void skipWhitespace() throws IOException {
        while (currentChar != null && " \t\r".indexOf(currentChar) >= 0) {
            advance();
        }
    }
    
    private void skipComment() throws IOException {
        while (currentChar != null && currentChar != '\n') {
            advance();
        }
    }
    
    public Token getNextToken() throws IOException, LexerException {
        while (currentChar != null) {
            if (" \t\r".indexOf(currentChar) >= 0) {
                skipWhitespace();
                continue;
            }
            
            if (currentChar == '/' && peek() == '/') {
                advance();
                advance();
                skipComment();
                continue;
            }
            
            if ("0123456789".indexOf(currentChar) >= 0) {
                return _number_();
            }
            
            if (Character.isAlphabetic(currentChar)) {
                return _word_();
            }
            
            if (currentChar == '"') {
                advance();
                return _string_();
            }
            
            if (peek() != null) {
                String tTypeStr = currentChar.toString() + peek().toString();
                if (tokenTypes.containsKey(tTypeStr)) {
                    TokenType tType = tokenTypes.get(tTypeStr);
                    advance();
                    advance();
                    return new Token(tType, null);
                }
            }
            
            if (tokenTypes.containsKey(currentChar.toString())) {
                TokenType tType = tokenTypes.get(currentChar.toString());
                advance();
                return new Token(tType, null);
            } else {
                throw new LexerException("cannot parse input into tokens");
            }
        }
        
        return new Token(
                TokenType.EOF, null
        );
    }
}
