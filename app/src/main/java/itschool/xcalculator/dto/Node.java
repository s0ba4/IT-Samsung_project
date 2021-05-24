package itschool.xcalculator.dto;

import static itschool.xcalculator.dto.Token.TokenType.FUNCTION;
import static itschool.xcalculator.dto.Token.TokenType.OPERATOR;

public class Node {

    private Token token;
    private Polynomial polynomial;
    public Node left;
    public Node right;

    public Node(Token token, Node left, Node right) {
        this.token = token;
        this.left = left;
        this.right = right;
    }

    public Node(Token token, Node left) {
        this.token = token;
        this.left = left;
    }

    public Node(Polynomial polynomial) {
        this.polynomial = polynomial;
    }

    public boolean isPolynomial() {
        return polynomial != null;
    }

    public boolean isOperator() {
        return token != null && token.type == OPERATOR;
    }

    public boolean isFunction() {
        return token != null && token.type == FUNCTION;
    }

    public Token getToken() {
        return token;
    }

    public Polynomial getPolynomial() {
        return polynomial;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

}
