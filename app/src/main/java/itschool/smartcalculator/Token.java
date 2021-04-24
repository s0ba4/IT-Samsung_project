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
        switch (type){
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
