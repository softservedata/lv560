package com.softserve.traning;

public interface B extends  A {

//    void m1();
    @Override
    default void m1() {
        System.out.println("B");
        //((A)this).m1();
        A.super.m1();
    }
}
