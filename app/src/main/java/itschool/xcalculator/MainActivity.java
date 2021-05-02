package itschool.xcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
        button.setOnClickListener(v -> {
            Parser parser = new Parser();
            PostfixConverter converter = new PostfixConverter();
            NumericCalculator calculator = new NumericCalculator();
            String expression = editText.getText().toString();
            double result = calculator.calculate(converter.convert(parser.parse(expression)));
            Toast.makeText(getApplicationContext(), String.valueOf(result), Toast.LENGTH_SHORT).show();

        });
    }
}