package com.softserve.edu;

public final class Appl {

    public static void main(String[] args) {
        Calc calc = new Calc();
        System.out.println("1 + 2 + ... = " + calc.add(1, 2));
        System.out.println("done");
    }
}
