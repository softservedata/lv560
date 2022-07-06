package edu09;

import com.softserve.edu09.Calc;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(value = Parameterized.class)
public class CalcParamTest {
    private static Calc calc;
    private double arg0;
    private double arg1;
    private double resultAdd;
    private double resultDiv;

    public CalcParamTest(double arg0, double arg1, double resultAdd, double resultDiv) {
        this.arg0 = arg0;
        this.arg1 = arg1;
        this.resultAdd = resultAdd;
        this.resultDiv = resultDiv;
        System.out.println("Constructor CalcParamTest() done");
    }

    @BeforeClass
    public static void setUpBeforeClass() {
        System.out.println("@BeforeClass setUpBeforeClass()");
        calc = new Calc();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][] {
                { 4, 4, 8, 1 }, { 20, 4, 24, 5 }, { 20, 8, 28, 2.5 }
        };
        return Arrays.asList(data);
    }

    @Test
    public void checkAdd() {
        //Calc calc = new Calc();
        double actual;
        double expected;
        //
        expected = resultAdd;
        actual = calc.add(arg0, arg1);
        //
        Assert.assertEquals("checkAdd()", expected, actual, 0.01);
    }

    @Test
    public void checkDiv() {
        //Calc calc = new Calc();
        double actual;
        double expected;
        //
        expected = resultDiv;
        actual = calc.div(arg0, arg1);
        //
        Assert.assertEquals("checkDiv()", expected, actual, 0.01);
    }
}
