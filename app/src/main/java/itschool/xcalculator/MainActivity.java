package itschool.xcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;

import itschool.xcalculator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    TextDecorator decorator;

    private void setupDigitButtons() {
        binding.digit0.setOnClickListener((v) -> insertSymbol(((Button) v).getText()));
        binding.digit1.setOnClickListener((v) -> insertSymbol(((Button) v).getText()));
        binding.digit2.setOnClickListener((v) -> insertSymbol(((Button) v).getText()));
        binding.digit3.setOnClickListener((v) -> insertSymbol(((Button) v).getText()));
        binding.digit4.setOnClickListener((v) -> insertSymbol(((Button) v).getText()));
        binding.digit5.setOnClickListener((v) -> insertSymbol(((Button) v).getText()));
        binding.digit6.setOnClickListener((v) -> insertSymbol(((Button) v).getText()));
        binding.digit7.setOnClickListener((v) -> insertSymbol(((Button) v).getText()));
        binding.digit8.setOnClickListener((v) -> insertSymbol(((Button) v).getText()));
        binding.digit9.setOnClickListener((v) -> insertSymbol(((Button) v).getText()));
        binding.dot.setOnClickListener((v) -> insertSymbol(((Button) v).getText()));
    }

    private void setupOperatorsButtons() {
        binding.leftBracket.setOnClickListener((v) -> insertSymbol(((Button) v).getText()));
        binding.rightBracket.setOnClickListener((v) -> insertSymbol(((Button) v).getText()));
        binding.sum.setOnClickListener((v) -> insertSymbol(((Button) v).getText()));
        binding.subtraction.setOnClickListener((v) -> insertSymbol(((Button) v).getText()));
        binding.multiplication.setOnClickListener((v) -> insertSymbol(((Button) v).getText()));
        binding.division.setOnClickListener((v) -> insertSymbol(((Button) v).getText()));
    }

    private void setupFunctionButtons() {
        binding.sin.setOnClickListener((v) -> insertSymbol("sin("));
        binding.cos.setOnClickListener((v) -> insertSymbol("cos("));
        binding.tg.setOnClickListener((v) -> insertSymbol("tg("));
        binding.ctg.setOnClickListener((v) -> insertSymbol("ctg("));
        binding.pi.setOnClickListener((v) -> insertSymbol(((Button) v).getText()));
        binding.exp.setOnClickListener((v) -> insertSymbol(((Button) v).getText()));
        binding.var.setOnClickListener((v) -> insertSymbol(((Button) v).getText()));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        decorator = new TextDecorator(getApplicationContext());
        setContentView(binding.getRoot());

        binding.input.setShowSoftInputOnFocus(false);
        binding.functionContainer.setOnClickListener((v) -> {
        });
        setupDigitButtons();
        setupOperatorsButtons();
        setupFunctionButtons();
        binding.clear.setOnClickListener((v) -> {
            int position = binding.input.getSelectionStart();
            if (position > 0) {
                binding.input.getText().delete(position - 1, position);
            }
        });
        binding.clear.setOnLongClickListener((v) -> {
            binding.input.setText("");
            binding.answer.setText("");
            return false;
        });
        binding.equals.setOnClickListener((v) -> {
            Parser parser = new Parser();
            PostfixConverter converter = new PostfixConverter();
            NumericCalculator calculator = new NumericCalculator();
            try {
                String expression = binding.input.getText().toString()
                        .replace('÷', '/')
                        .replace('×', '*');
                double result = calculator.calculate(converter.convert(parser.parse(expression)));
                binding.answer.setText(String.format("=%s", result));
            } catch (Exception exception) {
                binding.answer.setText("В выражении ошибка");
                exception.printStackTrace();
            }
        });

    }

    private void insertSymbol(CharSequence symbol) {
        int position = binding.input.getSelectionStart();
        Editable editable = binding.input.getText();
        editable.insert(position, symbol);
        editable.replace(0, editable.length(), decorator.decorate(editable));
    }

}