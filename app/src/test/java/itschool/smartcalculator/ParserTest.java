package itschool.smartcalculator;

import org.junit.Test;

import java.util.ArrayList;

public class ParserTest {
    @Test
    public void test1(){
        Parser parser = new Parser();
        ArrayList<Token> tokens = parser.parse("(29.34+sin(x)*12)/23");
        PostfixConverter converter = new PostfixConverter();
        System.out.println(converter.convert(tokens));

    }

}
