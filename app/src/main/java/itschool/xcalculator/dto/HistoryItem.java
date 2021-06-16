package itschool.xcalculator.dto;

public class HistoryItem {

    private String expression;
    private String answer;
    private int id;

    public HistoryItem(String expression, String answer, int id) {
        this.expression = expression;
        this.answer = answer;
        this.id = id;
    }

    public HistoryItem(String expression, String answer) {
        this.expression = expression;
        this.answer = answer;
    }

    public int getId() {
        return id;
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
