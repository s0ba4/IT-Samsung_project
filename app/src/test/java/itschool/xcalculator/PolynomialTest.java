package itschool.xcalculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import itschool.xcalculator.dto.Polynomial;

public class PolynomialTest {
    @Test
    public void testPlus() {
        Polynomial p1 = new Polynomial(1.0, 2.0, 3.0);
        Polynomial p2 = new Polynomial(4.0, 5.0, 6.0);
        Polynomial p3 = new Polynomial(5.0, 7.0, 9.0);
        Assertions.assertEquals(p1.plus(p2), p3);
    }
    @Test
    public void testMinus() {
        Polynomial p1 = new Polynomial(1.0, 2.0);
        Polynomial p2 = new Polynomial(1.0, 4.0, 6.0, 9.0);
        Polynomial p3 = new Polynomial(0.0, -2.0, -6.0, -9.0);
        Assertions.assertEquals(p1.minus(p2), p3);
    }
    @Test
    public void testMultiply() {
        Polynomial p1 = new Polynomial(1.0, 2.0);
        Polynomial p2 = new Polynomial(0.0, 1.0, 1.0);
        Polynomial p3 = new Polynomial(0.0, 1.0, 3.0, 2.0);
        Assertions.assertEquals(p1.multiply(p2), p3);
    }

}
