package com.softserve.edu;

import org.junit.Assert;
import org.junit.Test;

public class My {

    @Test
    public void testMy() {
        // From Maven
        System.out.println("My testMy1()");
        //
        Assert.assertEquals(9, 4 + 5);
    }

    //@Test
    public void testMy2() {
        System.out.println("My testMy2()");
        //
        int i = 0;
        i = 10 / i;
        System.out.println(" i = " + i);
    }

    @Test
    public void testMy3() {
        System.out.println("My testMy3()");
        //
        double i = 0;
        i = 10 / i;
        System.out.println(" i = " + i);
    }

    //@Test
    public void testMy4() {
        System.out.println("My testMy3()");
        //
        throw new RuntimeException("ha-ha-ha");
    }

    @Test(expected = ArithmeticException.class)//(expectedExceptions = ArithmeticException.class)
    public void divisionWithException() {
        System.out.println("\t\t\t@Test divisionWithException()");
        int i = 1 / 0;
    }

}
