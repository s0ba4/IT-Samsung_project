package itschool.xcalculator.dto;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HistoryItem)) return false;
        HistoryItem that = (HistoryItem) o;
        return getId() == that.getId() &&
                getExpression().equals(that.getExpression()) &&
                getAnswer().equals(that.getAnswer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getExpression(), getAnswer(), getId());
    }

    @Override
    public String toString() {
        return "HistoryItem{" +
                "expression='" + expression + '\'' +
                ", answer='" + answer + '\'' +
                ", id=" + id +
                '}';
    }
}
