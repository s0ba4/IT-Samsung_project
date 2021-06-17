package itschool.xcalculator;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import itschool.xcalculator.database.HistoryMapper;
import itschool.xcalculator.dto.HistoryItem;
import itschool.xcalculator.dto.Record;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HistoryMapperTest {

    @Test
    void test1() {
        HashMap<String, Object> row = new HashMap<>();
        row.put("_id", 1);
        row.put("expr", "(x+1)(x-1)");
        row.put("ans", "x^2+1");
        Record record = new Record(row);
        assertEquals(
                new HistoryItem("(x+1)(x-1)", "x^2+1", 1),
                HistoryMapper.map(record)
        );
    }
}
