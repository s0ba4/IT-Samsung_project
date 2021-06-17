package itschool.xcalculator.database;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.security.auth.callback.Callback;

import itschool.xcalculator.App;
import itschool.xcalculator.dto.HistoryItem;
import itschool.xcalculator.dto.Record;

public class DBWorker {

    private AppDatabase getDB(){
        return App.getInstance().getDatabase();
    }

    public void getAllAsync(Consumer<ArrayList<HistoryItem>> historyListener){
        Thread worker = new Thread(() -> {
            List<HistoryItem> historyItems = getDB()
                    .fetch("select * from history;")
                    .stream()
                    .map(HistoryMapper::map)
                    .collect(Collectors.toList());
            historyListener.accept(new ArrayList<>(historyItems));
        });
        worker.setDaemon(true);
        worker.start();
    }

    public void saveHistoryItemAsync(HistoryItem item){
        final String expr = item.getExpression();
        final String ans = item.getAnswer();
        Thread worker = new Thread(() -> {
            // добавление нового элемента в историю
            getDB().fetch("insert into history(expr, ans) values ('" + expr + "','" + ans + "');");
            // удаление всех старых элементов с сохранением последних 15 записей
            // решение взято с: https://stackoverflow.com/questions/3667085/delete-oldest-records-from-database
            getDB().fetch("delete from history where _id in (select _id from history order by _id desc limit -1 offset 15);");
        });
        worker.setDaemon(true);
        worker.start();
    }

}
