package sometime.interpreter;

import java.math.BigInteger;
import java.util.Scanner;
import sometime.ast.*;
import sometime.exception.NodeVisitorException;
import sometime.value.*;

public class Evaluator extends NodeVisitor {
    private final ToDoList toDoList;
    private final Environment env;
    private final int optLevel;
    private BigInteger currentLine;
    private final Scanner scan;
    
    public Evaluator(ToDoList toDoList, Environment env, int optLevel) {
        this.toDoList = toDoList;
        this.env = env;
        this.optLevel = optLevel;
        this.scan = new Scanner(this.env.in);
        
        this.currentLine = BigInteger.ZERO;
    }
    
    public Value evaluate(AST node, BigInteger line)
            throws IllegalArgumentException, NoSuchMethodException,
            NodeVisitorException {
        if (!(node instanceof ExpressionalAST)) {
            throw new IllegalArgumentException(
                    "attempt to evaluate non-expression"
            );
        }
        ExpressionalAST expr = (ExpressionalAST) node;
        
        if (optLevel >= 1) {
            if (expr.simpleValue != null) return expr.simpleValue;
        }
        
        currentLine = line;
        return (Value) visit(expr);
    }
    
    public BoolValue asBoolean(Value value)
            throws IllegalArgumentException, NullPointerException {
        if (value == null) {
            throw new NullPointerException(
                    "attempt to convert null value to boolean"
            );
        }
        
        if (value instanceof BoolValue v) return v;
        if (value instanceof StrValue) {
            throw new IllegalArgumentException(
                    "attempt to convert string value to boolean"
            );
        }
        BigInteger lineNum = (BigInteger) value.getValue();
        
        return new BoolValue(toDoList.getLineCount(lineNum).signum() > 0);
    }
    
    private boolean trySimplifyingAsBoolean(AST node)
            throws IllegalArgumentException {
        if (!(node instanceof ExpressionalAST)) return false;
        ExpressionalAST expr = (ExpressionalAST) node;
        
        if (expr.simpleValue == null) return false;
        if (expr.simpleValue instanceof BoolValue) return true;
        if (expr.simpleValue instanceof StrValue) {
            throw new IllegalArgumentException(
                    "attempt to simplify string value as boolean"
            );
        }
        
        BigInteger lineNum = (BigInteger) expr.simpleValue.getValue();
        if (toDoList.getLine(lineNum) != null) {
            if (lineNum.equals(currentLine)) {
                expr.simpleValue = new BoolValue(true);
                return true;
            } else {
                return false;
            }
        } else {
            expr.simpleValue = new BoolValue(false);
            return true;
        }
    }
    
    private void errorOnBoolean(Value... values)
            throws IllegalArgumentException {
        for (Value value : values) {
            if (value instanceof BoolValue) {
                throw new IllegalArgumentException(
                        "boolean given where number was expected"
                );
            }
        }
    }
    
    @Override
    Object visit(AST node) throws NoSuchMethodException, NodeVisitorException {
        ExpressionalAST expr = (ExpressionalAST) node;
        
        if (optLevel >= 1) {
            if (expr.simpleValue != null) return expr.simpleValue;
        }
        return super.visit(node);
    }
    
    Object visit_Num(AST node) {
        ExpressionalAST expr = (ExpressionalAST) node;
        
        if (optLevel >= 1) {
            if (expr.simpleValue == null) {
                expr.simpleValue = ((Num) expr).value;
            }
        }
        
        return ((Num) expr).value;
    }
    
    Object visit_Str(AST node) {
        ExpressionalAST expr = (ExpressionalAST) node;
        
        if (optLevel >= 1) {
            if (expr.simpleValue == null) {
                expr.simpleValue = ((Str) expr).value;
            }
        }
        
        return ((Str) expr).value;
    }
    
    Object visit_Bool(AST node) {
        ExpressionalAST expr = (ExpressionalAST) node;
        
        if (optLevel >= 1) {
            if (expr.simpleValue == null) {
                expr.simpleValue = ((Bool) expr).value;
            }
        }
        
        return ((Bool) expr).value;
    }
    
    Object visit_Operation(AST node) throws Exception {
        Operation opNode = (Operation) node;
        
        if (opNode.params.size() == 1) {
            return visit_OperationUnary(opNode);
        } else if (opNode.params.size() == 2) {
            return visit_OperationBinary(opNode);
        }
        
        throw new UnsupportedOperationException(
                "unexpected operation arity"
        );
    }
    
    Object visit_OperationUnary(Operation opNode) throws Exception {
        ExpressionalAST operandNode = opNode.params.get(0);
        Value operand = (Value) visit(operandNode);
        if (operand == null) {
            throw new NullPointerException(
                    "operand to unary operation is null"
            );
        }
        if (operand instanceof StrValue) {
            throw new IllegalArgumentException(
                    "string given to unary operation"
            );
        }
        
        boolean simplifies = operandNode.simpleValue != null;
        Value res;
        
        switch (opNode.op.getType()) {
            case NOT -> {
                if (operand instanceof NumValue) {
                    operand = asBoolean(operand);
                    if (optLevel >= 1) {
                        if (!trySimplifyingAsBoolean(opNode)) {
                            simplifies = false;
                        }
                    }
                }
                res = new BoolValue(
                        !((Boolean) operand.getValue())
                );
            }
            case MINUS -> {
                errorOnBoolean(operand);
                res = new NumValue(
                        ((BigInteger) operand.getValue()).negate()
                );
            }
            default -> {
                throw new UnsupportedOperationException(
                        "unexpected unary operation"
                );
            }
        }
        
        if (optLevel >= 1) {
            if (simplifies) opNode.simpleValue = res;
        }
        
        return res;
    }
    
    Object visit_OperationBinary(Operation opNode)
            throws IllegalArgumentException, NoSuchMethodException,
            NullPointerException, UnsupportedOperationException,
            NodeVisitorException {
        ExpressionalAST leftNode = opNode.params.get(0);
        ExpressionalAST rightNode = opNode.params.get(1);
        Value left = (Value) visit(leftNode);
        Value right = (Value) visit(rightNode);
        if (left == null || right == null) {
            throw new NullPointerException(
                    "operand(s) to binary operation are null"
            );
        }
        if (left instanceof StrValue || right instanceof StrValue) {
            throw new IllegalArgumentException(
                    "string given to unary operation"
            );
        }
        
        Value res;
        boolean simplifies = leftNode.simpleValue != null
                && rightNode.simpleValue != null;
        
        switch (opNode.op.getType()) {
            case PLUS -> {
                errorOnBoolean(left, right);
                res = new NumValue(
                        ((BigInteger) left.getValue())
                                .add((BigInteger) right.getValue())
                );
            }
            case MINUS -> {
                errorOnBoolean(left, right);
                res = new NumValue(
                        ((BigInteger) left.getValue())
                                .subtract((BigInteger) right.getValue())
                );
            }
            case MUL -> {
                errorOnBoolean(left, right);
                res = new NumValue(
                        ((BigInteger) left.getValue())
                                .multiply((BigInteger) right.getValue())
                );
            }
            case DIV -> {
                errorOnBoolean(left, right);
                res = new NumValue(
                        ((BigInteger) left.getValue())
                                .divide((BigInteger) right.getValue())
                );
            }
            case MOD -> {
                errorOnBoolean(left, right);
                res = new NumValue(
                        ((BigInteger) left.getValue())
                                .mod((BigInteger) right.getValue())
                );
            }
            case LT -> {
                errorOnBoolean(left, right);
                res = new BoolValue(
                        ((BigInteger) left.getValue())
                                .compareTo((BigInteger) right.getValue()) < 0
                );
            }
            case GT -> {
                errorOnBoolean(left, right);
                res = new BoolValue(
                        ((BigInteger) left.getValue())
                                .compareTo((BigInteger) right.getValue()) > 0
                );
            }
            case LEQ -> {
                errorOnBoolean(left, right);
                res = new BoolValue(
                        ((BigInteger) left.getValue())
                                .compareTo((BigInteger) right.getValue()) <= 0
                );
            }
            case GEQ -> {
                errorOnBoolean(left, right);
                res = new BoolValue(
                        ((BigInteger) left.getValue())
                                .compareTo((BigInteger) right.getValue()) >= 0
                );
            }
            case EQU -> {
                if (left instanceof BoolValue && right instanceof NumValue) {
                    right = asBoolean(right);
                    if (optLevel >= 1) {
                        if (
                                !trySimplifyingAsBoolean(leftNode)
                                || !trySimplifyingAsBoolean(rightNode)
                                ) {
                            simplifies = false;
                        }
                    }
                }
                
                if (right instanceof BoolValue && left instanceof NumValue) {
                    left = asBoolean(left);
                    if (optLevel >= 1) {
                        if (
                                !trySimplifyingAsBoolean(leftNode)
                                || !trySimplifyingAsBoolean(rightNode)
                                ) {
                            simplifies = false;
                        }
                    }
                }
                res = new BoolValue(left.equals(right));
            }
            case NEQ -> {
                if (left instanceof BoolValue && right instanceof NumValue) {
                    right = asBoolean(right);
                    if (optLevel >= 1) {
                        if (
                                !trySimplifyingAsBoolean(leftNode)
                                || !trySimplifyingAsBoolean(rightNode)
                                ) {
                            simplifies = false;
                        }
                    }
                }
                
                if (right instanceof BoolValue && left instanceof NumValue) {
                    left = asBoolean(left);
                    if (optLevel >= 1) {
                        if (
                                !trySimplifyingAsBoolean(leftNode)
                                || !trySimplifyingAsBoolean(rightNode)
                                ) {
                            simplifies = false;
                        }
                    }
                }
                res = new BoolValue(!left.equals(right));
            }
            case AND -> {
                if (left instanceof NumValue) {
                    left = asBoolean(left);
                    if (optLevel >= 1) {
                        if (!trySimplifyingAsBoolean(leftNode)) {
                            simplifies = false;
                        }
                    }
                }
                if (right instanceof NumValue) {
                    right = asBoolean(right);
                    if (optLevel >= 1) {
                        if (!trySimplifyingAsBoolean(rightNode)) {
                            simplifies = false;
                        }
                    }
                }
                res = new BoolValue(
                        (Boolean) left.getValue()
                                && (Boolean) right.getValue()
                );
            }
            case OR -> {
                if (left instanceof NumValue) {
                    left = asBoolean(left);
                    if (optLevel >= 1) {
                        if (!trySimplifyingAsBoolean(leftNode)) {
                            simplifies = false;
                        }
                    }
                }
                if (right instanceof NumValue) {
                    right = asBoolean(right);
                    if (optLevel >= 1) {
                        if (!trySimplifyingAsBoolean(rightNode)) {
                            simplifies = false;
                        }
                    }
                }
                res = new BoolValue(
                        (Boolean) left.getValue()
                                || (Boolean) right.getValue()
                );
            }
            default -> {
                throw new UnsupportedOperationException(
                        "unexpected binary operation"
                );
            }
        }
        
        if (optLevel >= 1) {
            if (simplifies) opNode.simpleValue = res;
        }
        
        return res;
    }
    
    Object visit_Function(AST node)
            throws IllegalArgumentException, NoSuchMethodException,
            UnsupportedOperationException, NodeVisitorException {
        Function funcNode = (Function) node;
        
        switch (funcNode.op.getType()) {
            case NFUNC -> {
                if (funcNode.params.size() != 1) {
                    throw new IllegalArgumentException(
                            "wrong number of arguments to N()"
                    );
                }
                
                ExpressionalAST paramNode = funcNode.params.get(0);
                BigInteger paramValue =
                        ((NumValue) visit(paramNode)).getValue();
                
                return new NumValue(toDoList.getLineCount(paramValue));
            }
            case UFUNC -> {
                if (funcNode.params.size() != 1) {
                    throw new IllegalArgumentException(
                            "wrong number of arguments to U()"
                    );
                }
                
                ExpressionalAST paramNode = funcNode.params.get(0);
                BigInteger paramValue =
                        ((NumValue) visit(paramNode)).getValue();
                
                StrValue res = new StrValue(new String(
                        Character.toChars(paramValue.intValueExact())
                ));
                
                if (optLevel >= 1) {
                    if (paramNode.simpleValue == null) paramNode.simpleValue = res;
                }
                
                return res;
            }
            case READ -> {
                if (!funcNode.params.isEmpty()) {
                    throw new IllegalArgumentException(
                            "wrong number of arguments to read()"
                    );
                }
                
                String readString;
                BigInteger res;
                while (true) { // haha, bad practice
                    readString = scan.nextLine();
                    try {
                        res = new BigInteger(readString);
                        break;
                    } catch (NumberFormatException e) {
                        // my IDE is telling me not to put a continue here
                    }
                }
                return new NumValue(res);
            }
            default -> {
                throw new UnsupportedOperationException(
                        "unexpected identifier in function call"
                );
            }
        }
    }
}
