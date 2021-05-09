package itschool.xcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.widget.EditText;

import itschool.xcalculator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    TextDecorator decorator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        decorator = new TextDecorator();
        setContentView(binding.getRoot());

        binding.input.setShowSoftInputOnFocus(false);
        binding.functionContainer.setOnClickListener((v) -> {});

        binding.digit0.setOnClickListener((v) -> insertSymbol("0"));
        binding.digit1.setOnClickListener((v) -> insertSymbol("1"));
        binding.digit2.setOnClickListener((v) -> insertSymbol("2"));
        binding.digit3.setOnClickListener((v) -> insertSymbol("3"));
        binding.digit4.setOnClickListener((v) -> insertSymbol("4"));
        binding.digit5.setOnClickListener((v) -> insertSymbol("5"));
        binding.digit6.setOnClickListener((v) -> insertSymbol("6"));
        binding.digit7.setOnClickListener((v) -> insertSymbol("7"));
        binding.digit8.setOnClickListener((v) -> insertSymbol("8"));
        binding.digit9.setOnClickListener((v) -> insertSymbol("9"));
        binding.digit0.setOnClickListener((v) -> insertSymbol("0"));
        binding.dot.setOnClickListener((v) -> insertSymbol("."));
        binding.leftBracket.setOnClickListener((v) -> insertSymbol("("));
        binding.rightBracket.setOnClickListener((v) -> insertSymbol(")"));
        binding.sum.setOnClickListener((v) -> insertSymbol("+"));
        binding.subtraction.setOnClickListener((v) -> insertSymbol("-"));
        binding.multiplication.setOnClickListener((v) -> insertSymbol("×"));
        binding.division.setOnClickListener((v) -> insertSymbol("÷"));
        binding.clear.setOnClickListener((v) -> {
            int position = binding.input.getSelectionStart();
            if (position > 0) {
                binding.input.getText().delete(position - 1, position);
            }
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
        binding.sin.setOnClickListener((v) -> insertSymbol("sin("));
        binding.cos.setOnClickListener((v) -> insertSymbol("cos("));
        binding.tg.setOnClickListener((v) -> insertSymbol("tg("));
        binding.ctg.setOnClickListener((v) -> insertSymbol("ctg("));
        binding.pi.setOnClickListener((v) -> insertSymbol("π"));
        binding.exp.setOnClickListener((v) -> insertSymbol("e"));
    }

    private void insertSymbol(String symbol) {
        int position = binding.input.getSelectionStart();
        Editable editable = binding.input.getText();
        editable.insert(position, symbol);
        editable.replace(0, editable.length(), decorator.decorate(editable));
    }

}