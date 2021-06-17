package itschool.xcalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;

import itschool.xcalculator.database.DBWorker;
import itschool.xcalculator.databinding.ActivityMainBinding;
import itschool.xcalculator.domain.calculator.GeneralCalculator;
import itschool.xcalculator.dto.HistoryItem;
import itschool.xcalculator.dto.TrigonometricMode;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    TextDecorator decorator;
    GeneralCalculator generalCalculator;
    TrigonometricMode mode = TrigonometricMode.DEGREES;
    HistoryAdapter adapter;
    DBWorker dbWorker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbWorker = new DBWorker();
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        decorator = new TextDecorator(getApplicationContext());
        generalCalculator = new GeneralCalculator();
        setContentView(binding.getRoot());
        initHistoryRecycler();
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
            try {
                String expression = binding.input.getText().toString()
                        .replace('÷', '/')
                        .replace('×', '*');
                String result = generalCalculator.calculate(expression, mode)
                        .replace('/', '÷')
                        .replace('*', '×');
                binding.answer.setText(decorator.decorate(String.format("= %s", result)));
                HistoryItem item = new HistoryItem(expression, result);
                adapter.insertItem(item);
                waitForRecyclerViewAndScrollDown();
                dbWorker.saveHistoryItemAsync(item);
            } catch (Exception exception) {
                binding.answer.setText("В выражении ошибка");
                exception.printStackTrace();
            }
        });
        renderButtons();
        binding.scrollView.setSmoothScrollingEnabled(false);
        dbWorker.getAllAsync(historyItems -> {
            adapter.setData(historyItems);
            waitForRecyclerViewAndScrollDown();
            HistoryItem lastItem = historyItems.get(historyItems.size() - 1);
            binding.input.setText(decorator.decorate(lastItem.getExpression()));
            binding.answer.setText(decorator.decorate(String.format("= %s", lastItem.getAnswer())));
            binding.input.setSelection(lastItem.getExpression().length());
        });
    }

    private void waitForRecyclerViewAndScrollDown() {
        binding.historyRecycler
                .getViewTreeObserver()
                .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        binding.scrollView.fullScroll(View.FOCUS_DOWN);
                        binding.historyRecycler
                                .getViewTreeObserver()
                                .removeOnGlobalLayoutListener(this);
                    }
                });
    }

    private void initHistoryRecycler() {
        adapter = new HistoryAdapter();
        binding.historyRecycler.setLayoutManager(new LinearLayoutManager(this));
        binding.historyRecycler.setAdapter(adapter);
    }

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
        binding.rad.setOnClickListener((v) -> {
            mode = TrigonometricMode.RADIANS;
            renderButtons();
        });
        binding.deg.setOnClickListener((v) -> {
            mode = TrigonometricMode.DEGREES;
            renderButtons();
        });
        // binding.ctg.setOnClickListener((v) -> insertSymbol("ctg("));
        binding.pi.setOnClickListener((v) -> insertSymbol(((Button) v).getText()));
        binding.exp.setOnClickListener((v) -> insertSymbol(((Button) v).getText()));
        binding.var.setOnClickListener((v) -> insertSymbol("x"));
        binding.power.setOnClickListener((v) -> insertSymbol(((Button) v).getText()));
    }

    private void renderButtons() {
        if (mode == TrigonometricMode.RADIANS) {
            binding.rad.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.blueAccent)));
            binding.rad.setTextColor(getResources().getColor(R.color.backgroundDark));
            binding.deg.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.primaryButtonBackground)));
            binding.deg.setTextColor(Color.WHITE);
        } else {
            binding.deg.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.blueAccent)));
            binding.deg.setTextColor(getResources().getColor(R.color.backgroundDark));
            binding.rad.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.primaryButtonBackground)));
            binding.rad.setTextColor(Color.WHITE);
        }
    }

    private void insertSymbol(CharSequence symbol) {
        int position = binding.input.getSelectionStart();
        Editable editable = binding.input.getText();
        editable.insert(position, symbol);
        editable.replace(0, editable.length(), decorator.decorate(editable));
    }

}