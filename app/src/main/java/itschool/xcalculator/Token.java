package itschool.xcalculator;

public class Token {
    public final TokenType type;
    public final String content;

    public Token(TokenType type, String content) {
        this.type = type;
        this.content = content;
    }

    public boolean isLeftBracket() {
        return type == TokenType.OPERATOR && content.equals("(");
    }

    public boolean isRightBracket() {
        return type == TokenType.OPERATOR && content.equals(")");
    }

    public int getPriority() {
        if (type == TokenType.OPERATOR) {
            if (content.equals("+") || content.equals("-")) {
                return 1;
            }
            if (content.equals("*") || content.equals("/")) {
                return 2;
            }
            if (content.equals("^")) {
                return 3;
            }
        }
        return 0;
    }

    @Override
    public String toString() {
        switch (type) {
            case NUMBER:
                return "NUMBER(" + content + ")";
            case OPERATOR:
                return "OPERATOR(" + content + ")";
            case FUNCTION:
                return "FUNCTION(" + content + ")";
            case VARIABLE:
                return "x";
        }
        return null;
    }

    enum TokenType {NUMBER, OPERATOR, FUNCTION, VARIABLE}
}
