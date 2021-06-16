package itschool.xcalculator;

import android.app.Application;

import itschool.xcalculator.database.AppDatabase;
import itschool.xcalculator.database.DBHelper;

public class App extends Application {

    private static App instance;
    private AppDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = new AppDatabase(
                new DBHelper(getApplicationContext(),"XCalculatorDB", 1)
        );
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        database.close();
    }

    public static App getInstance(){
        return instance;
    }

    public AppDatabase getDatabase() {
        return database;
    }

}
