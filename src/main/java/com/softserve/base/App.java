package com.softserve.base;

import com.softserve.base.next.Child;

public class App {

    public static void main(String[] args) {
        // Parent p = new Parent(); // 1
        // Parent p = new Child();  // 1
        Parent p = new GrandChild(); // 3
        //
        // Child p = new Child(); // Compile Error
        // GrandChild p = new GrandChild(); // 3
        System.out.println("f() = " + p.f());
    }
}
