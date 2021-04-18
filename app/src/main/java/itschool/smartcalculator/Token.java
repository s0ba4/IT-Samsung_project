package itschool.smartcalculator;

public class Token {
    private TokenType type;
    private String content;

    public Token(TokenType type, String content) {
        this.type = type;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Token{" + type +
                ", content='" + content + '\'' +
                '}';
    }

    enum TokenType {NUMBER, OPERATOR, FUNCTION}
}
