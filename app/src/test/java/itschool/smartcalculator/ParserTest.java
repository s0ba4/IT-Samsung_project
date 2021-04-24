package itschool.smartcalculator;

import org.junit.Test;

public class ParserTest {
    @Test
    public void test1(){
        Parser parser = new Parser();
        System.out.println(parser.parse("((29.34+sin(x))*12)/23"));
    }

}
