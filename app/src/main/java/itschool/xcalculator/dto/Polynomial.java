package itschool.xcalculator.dto;

import androidx.annotation.NonNull;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.BiFunction;

import itschool.xcalculator.domain.Node;

public class Polynomial implements Node {
    private final double[] coeffs;

    public Polynomial(double... coeffs) {
        this.coeffs = coeffs;
    }

    public Polynomial(int... coeffs) {
        final double[] doubles = new double[coeffs.length];
        for (int i = 0; i < coeffs.length; i++) doubles[i] = coeffs[i];
        this.coeffs = doubles;
    }

    /**
     * <h1>Сложение полиномов</h1>
     *
     * <h2>Пример</h2>
     *
     * <code>
     *     Polynomial p1 = new Polynomial(1, 2); // 1 + 2x<br>
     *     Polynomial p2 = new Polynomial(1, 4, 6, 9); // 1 + 4x + 6x^2 + 9x^3<br>
     *     p1.plus(p2) // 2 + 6x + 6x^2 + 9x^3<br>
     * </code>
     *
     * @param other полином, который нужно вычесть из текущего
     * @return сумма полиномов - новый полином
     */

    public Polynomial plus(Polynomial other) {
        return (other.coeffs.length > coeffs.length)
                ? doOperation(coeffs, other.coeffs, Double::sum)
                : doOperation(other.coeffs, coeffs, Double::sum);
    }

    /**
     * <h1>Вычитание полиномов</h1>
     *
     * <h2>Пример</h2>
     *
     * <code>
     *     Polynomial p1 = new Polynomial(1, 2); // 1 + 2x<br>
     *     Polynomial p2 = new Polynomial(1, 4, 6, 9); // 1 + 4x + 6x^2 + 9x^3<br>
     *     p1.minus(p2) // -2x - 6x^2 - 9x^3<br>
     * </code>
     *
     * @param other полином, который нужно прибавить к текущему
     * @return разность полиномов - новый полином
     */

    public Polynomial minus(Polynomial other) {
        return (other.coeffs.length > coeffs.length)
                ? doOperation(coeffs, other.coeffs, (a, b) -> b - a)
                : doOperation(other.coeffs, coeffs, (a, b) -> b - a);
    }

    /**
     * <h1>Умножение полинома на полином</h1>
     *
     * <h2>Пример</h2>
     *
     * <code>
     *     Polynomial p1 = new Polynomial(1, 2); // 1 + 2x<br>
     *     Polynomial p2 = new Polynomial(0, 1, 1); // x + x^2<br>
     *     p1.multiply(p2) // x + 3x^2 + 2x^3<br>
     * </code>
     *
     * @param other полином, на который нужно умножить текущий полином
     * @return произведение полиномов - новый полином
     */

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

    /**
     * <h1>Порядок полинома</h1>
     *
     * Возвращает порядок полинома.
     *
     * <h2>Пример</h2>
     *
     * <code>
     * // вернет 3, потому что x + 2x+ x^2 + x^3<br>
     * new Polynomial(0, 2, 1, 1).getOrder() <br>
     * </code>
     *
     * @return порядок полинома (число от 0 до Integer.MAX_VALUE)
     */

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
