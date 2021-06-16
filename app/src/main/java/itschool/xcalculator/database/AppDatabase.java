package itschool.xcalculator.database;

import android.database.Cursor;

import org.intellij.lang.annotations.Language;

import java.util.ArrayList;
import java.util.HashMap;

import itschool.xcalculator.dto.Record;

public class AppDatabase {

    private final DBHelper dbHelper;

    public AppDatabase(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public ArrayList<Record> fetch(@Language("RoomSql") String request) {
        Cursor cursor = dbHelper.getWritableDatabase().rawQuery(request, null);
        if (cursor.getCount() == 0) {
            cursor.close();
            return new ArrayList<>();
        }
        String[] columns = cursor.getColumnNames();
        ArrayList<Record> records = new ArrayList<>();
        boolean eof = cursor.moveToFirst();
        while (eof) {
            HashMap<String, Object> row = new HashMap<>();
            for (String column:columns){
                row.put(column, get(cursor, column));
            }
            records.add(new Record(row));
            eof = cursor.moveToNext();
        }
        cursor.close();
        return records;
    }

    public void close(){
        dbHelper.close();
    }

    private Object get(Cursor cursor, String columnName){
        int columnIndex = cursor.getColumnIndex(columnName);
        switch(cursor.getType(columnIndex)) {
            case Cursor.FIELD_TYPE_INTEGER: return cursor.getInt(columnIndex);
            case Cursor.FIELD_TYPE_FLOAT: return cursor.getFloat(columnIndex);
            case Cursor.FIELD_TYPE_STRING: return cursor.getString(columnIndex);
            case Cursor.FIELD_TYPE_BLOB: return cursor.getBlob(columnIndex);
        }
        return null;
    }

}
