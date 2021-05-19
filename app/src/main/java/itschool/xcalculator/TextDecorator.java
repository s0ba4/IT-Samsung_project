package itschool.xcalculator;

import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

import java.util.Arrays;
import java.util.HashSet;

public class TextDecorator {
    private final HashSet<Character> pinkChars = new HashSet<>(Arrays.asList('+', '-', '*', '/', '=', '÷', '×'));
    private final HashSet<Character> blueChars = new HashSet<>(Arrays.asList('(', ')'));

    private final int pinkForegroundColor;
    private final int blueForegroundColor;

    public TextDecorator(Context context) {
        pinkForegroundColor = context.getResources().getColor(R.color.pinkAccent);
        blueForegroundColor = context.getResources().getColor(R.color.blueAccent);
    }

    public CharSequence decorate(CharSequence sequence) {
        SpannableString text = SpannableString.valueOf(sequence);
        int i = 0;
        final int length = text.length();
        while (i < length) {
            char currentChar = text.charAt(i);
            if (pinkChars.contains(currentChar)) {
                text.setSpan(new ForegroundColorSpan(pinkForegroundColor), i, i + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            } else if (blueChars.contains(currentChar)) {
                text.setSpan(new ForegroundColorSpan(blueForegroundColor), i, i + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            i++;
        }
        return text;
    }
}
