package itschool.xcalculator.dto;

import androidx.annotation.NonNull;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.BiFunction;

public class Polynomial {
    private final double[] coeffs;

    public Polynomial(double... coeffs) {
        this.coeffs = coeffs;
    }

    public Polynomial(int... coeffs) {
        final double[] doubles = new double[coeffs.length];
        for (int i = 0; i < coeffs.length; i++) doubles[i] = coeffs[i];
        this.coeffs = doubles;
    }

    public Polynomial plus(Polynomial other) {
        return (other.coeffs.length > coeffs.length)
                ? doOperation(coeffs, other.coeffs, Double::sum)
                : doOperation(other.coeffs, coeffs, Double::sum);
    }

    public Polynomial minus(Polynomial other) {
        return (other.coeffs.length > coeffs.length)
                ? doOperation(coeffs, other.coeffs, (a, b) -> a - b)
                : doOperation(other.coeffs, coeffs, (a, b) -> a - b);
    }

    public Polynomial multiply(Polynomial other) {
        Polynomial polynomial = null;
        for (int i = 0; i < coeffs.length; i++) {
            final double[] newCoeffs = new double[i + other.coeffs.length];
            Arrays.fill(newCoeffs, 0.0);
            for (int j = i; j < i + other.coeffs.length; j++) {
                newCoeffs[j] = coeffs[i] * other.coeffs[j - i];
            }
            polynomial = (polynomial == null)
                    ? new Polynomial(newCoeffs)
                    : polynomial.plus(new Polynomial(newCoeffs));
        }
        return Objects.requireNonNull(polynomial);
    }

    public int getOrder() {
        return coeffs.length - 1;
    }

    private Polynomial doOperation(
            double[] shortest,
            double[] longest,
            BiFunction<Double, Double, Double> function
    ) {
        double[] result = new double[longest.length];
        for (int i = 0; i < shortest.length; i++) {
            result[i] = function.apply(shortest[i], longest[i]);
        }
        for (int i = shortest.length; i < longest.length; i++) {
            result[i] = function.apply(0.0, longest[i]);
        }
        return new Polynomial(result);
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < coeffs.length; i++) {
            final double coeff = coeffs[i];
            if (coeff == 0.0) continue;
            if (coeff > 0) builder.append('+');
            if (i == 0) {
                builder.append(coeff);
            } else if (i == 1) {
                builder.append(coeff).append('x');
            } else {
                builder.append(coeff).append("x^").append(i);
            }
        }
        return String.format("P(%s)", builder.toString());
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
