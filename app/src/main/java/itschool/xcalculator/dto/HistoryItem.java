package itschool.xcalculator.dto;

public class HistoryItem {

    private String expression;
    private String answer;

    public HistoryItem(String expression, String answer) {
        this.expression = expression;
        this.answer = answer;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
