package itschool.smartcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText editText = findViewById(R.id.editText);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(v -> Toast.makeText(getApplicationContext(), editText.getText(), Toast.LENGTH_SHORT).show());
    }
}