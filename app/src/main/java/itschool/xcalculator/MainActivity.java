package itschool.xcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;

import itschool.xcalculator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.input.setShowSoftInputOnFocus(false);


        binding.digit0.setOnClickListener((v) -> {
            int position = binding.input.getSelectionStart();
            binding.input.getText().insert(position, "0");
        });
        binding.digit1.setOnClickListener((v) -> {
            int position = binding.input.getSelectionStart();
            binding.input.getText().insert(position, "1");
        });
        binding.digit2.setOnClickListener((v) -> {
            int position = binding.input.getSelectionStart();
            binding.input.getText().insert(position, "2");
        });
        binding.digit3.setOnClickListener((v) -> {
            int position = binding.input.getSelectionStart();
            binding.input.getText().insert(position, "3");
        });
        binding.digit4.setOnClickListener((v) -> {
            int position = binding.input.getSelectionStart();
            binding.input.getText().insert(position, "4");
        });
        binding.digit5.setOnClickListener((v) -> {
            int position = binding.input.getSelectionStart();
            binding.input.getText().insert(position, "5");
        });
        binding.digit6.setOnClickListener((v) -> {
            int position = binding.input.getSelectionStart();
            binding.input.getText().insert(position, "6");
        });
        binding.digit7.setOnClickListener((v) -> {
            int position = binding.input.getSelectionStart();
            binding.input.getText().insert(position, "7");
        });
        binding.digit8.setOnClickListener((v) -> {
            int position = binding.input.getSelectionStart();
            binding.input.getText().insert(position, "8");
        });
        binding.digit9.setOnClickListener((v) -> {
            int position = binding.input.getSelectionStart();
            binding.input.getText().insert(position, "9");
        });
        binding.digit0.setOnClickListener((v) -> {
            int position = binding.input.getSelectionStart();
            binding.input.getText().insert(position, "0");
        });
        binding.dot.setOnClickListener((v) -> {
            int position = binding.input.getSelectionStart();
            binding.input.getText().insert(position, ".");
        });
        binding.leftBracket.setOnClickListener((v) -> {
            int position = binding.input.getSelectionStart();
            binding.input.getText().insert(position, "(");
        });
        binding.rightBracket.setOnClickListener((v) -> {
            int position = binding.input.getSelectionStart();
            binding.input.getText().insert(position, ")");
        });
        binding.division.setOnClickListener((v) -> {
            int position = binding.input.getSelectionStart();
            binding.input.getText().insert(position, "/");
        });
        binding.multiplication.setOnClickListener((v) -> {
            int position = binding.input.getSelectionStart();
            binding.input.getText().insert(position, "*");
        });
        binding.subtraction.setOnClickListener((v) -> {
            int position = binding.input.getSelectionStart();
            binding.input.getText().insert(position, "-");
        });
        binding.sum.setOnClickListener((v) -> {
            int position = binding.input.getSelectionStart();
            binding.input.getText().insert(position, "+");
        });
        binding.clear.setOnClickListener((v) -> {
            int position = binding.input.getSelectionStart();
            if (position > 0) {
                binding.input.getText().delete(position - 1, position);
            }
        });
    }
}