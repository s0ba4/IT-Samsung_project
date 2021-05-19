package itschool.xcalculator.dto;

import java.util.Arrays;
import java.util.function.BiFunction;

public class Polynomial {
    private final Double[] coeffs;

    public Polynomial(Double... coeffs) {
        this.coeffs = coeffs;
    }

    public Polynomial plus(Polynomial other) {
        if (coeffs.length > other.coeffs.length) {
            return doOperation(other.coeffs, coeffs, (n1, n2) -> n1 + n2);
        } else {
            return doOperation(coeffs, other.coeffs, (n1, n2) -> n1 + n2);
        }
    }

    public Polynomial minus(Polynomial other) {
        if (coeffs.length > other.coeffs.length) {
            return doOperation(other.coeffs, coeffs, (n1, n2) -> n2 - n1);
        } else {
            return doOperation(coeffs, other.coeffs, (n1, n2) -> n2 - n1);
        }
    }

    private Polynomial doOperation(
            Double[] shortest,
            Double[] longest,
            BiFunction<Double, Double, Double> function
    ) {
        Double[] result = new Double[longest.length];
        for (int i = 0; i < shortest.length; i++) {
            result[i] = function.apply(shortest[i], longest[i]);
        }
        for (int i = shortest.length; i < longest.length; i++) {
            result[i] = longest[i];
        }
        return new Polynomial(result);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Polynomial)) return false;
        Polynomial that = (Polynomial) o;
        return Arrays.equals(coeffs, that.coeffs);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(coeffs);
    }
}
