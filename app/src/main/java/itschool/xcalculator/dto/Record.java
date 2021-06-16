package itschool.xcalculator.dto;

import java.util.HashMap;

public class Record {

    private final HashMap<String, Object> row;


    public Record(HashMap<String, Object> row) {
        this.row = row;
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String key) {
        return (T) row.get(key);
    }

}
