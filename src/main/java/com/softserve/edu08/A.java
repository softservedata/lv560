package com.softserve.edu08;

public class A { // extends Object
    private int i = 12;

    public A() {
        i = 120;
        System.out.println("constructor A()");
    }

    public A(int i) {
        this.i = i;
        System.out.println("constructor A(int i)");
    }

    public int getI() {
        int k = 1;
        System.out.println("\tgetI() from A");
        return i;
    }

}
