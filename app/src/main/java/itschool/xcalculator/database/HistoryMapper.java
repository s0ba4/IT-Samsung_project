package itschool.xcalculator.database;

import itschool.xcalculator.dto.HistoryItem;
import itschool.xcalculator.dto.Record;

public class HistoryMapper {

    public static HistoryItem map(Record record){
        int id = record.get("_id");
        String expression = record.get("expr");
        String answer = record.get("ans");

        return new HistoryItem(expression, answer, id);
    }

}
