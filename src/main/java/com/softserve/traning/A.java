package com.softserve.traning;

public interface A {

    // void m1();
    default void m1() {
        System.out.println("A");
        m3();
    }

    static void m3() {
        System.out.println("A m3");
    }
}
