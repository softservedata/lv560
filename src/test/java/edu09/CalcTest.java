package edu09;

import com.softserve.edu09.Calc;
import org.junit.*;

public class CalcTest {
    private static Calc calc;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        System.out.println("@BeforeClass setUpBeforeClass()");
        calc = new Calc();
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        System.out.println("@AfterClass tearDownAfterClass()");
    }

    @Before
    public void setUp() throws Exception {
        System.out.println("\t@Before setUp()");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("\t@After tearDown()");
    }


    @Test
    public void checkAddIdentity() {
        System.out.println("\t\t@Test checkAddIdentity()");
        //Calc calc = new Calc();
        double actual;
        double expected;
        //
        expected = 8.001;
        actual = calc.add(4, 4);
        //
        Assert.assertEquals("checkAddIdentity()", expected, actual, 0.01);
    }

    @Test
    public void checkAddDifferent() {
        System.out.println("\t\t@Test checkAddDifferent()");
        //Calc calc = new Calc();
        double actual;
        double expected;
        //
        expected = 6.001;
        actual = calc.add(4, 2);
        //
        Assert.assertEquals("checkAddDifferent()", expected, actual, 0.01);
    }

    @Test
    public void checkDivInt() {
        System.out.println("\t\t@Test checkDivInt()");
        //Calc calc = new Calc();
        double actual;
        double expected;
        //
        expected = 5.001;
        actual = calc.div(20, 4);
        //
        Assert.assertEquals("checkDivInt()", expected, actual, 0.01);
    }

    @Test
    public void checkDivDouble() {
        System.out.println("\t\t@Test checkDivDouble()");
        //Calc calc = new Calc();
        double actual;
        double expected;
        //
        expected = 2.5;
        actual = calc.div(20, 8);
        //
        Assert.assertEquals("checkDivDouble()", expected, actual, 0.01);
    }
}
