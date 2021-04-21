package com.softserve.edu.parent;

public class Parent {

    // protected int f() {
    // private final int f() {
    int f() {
        return 1;
    }

    public int useF() {
        return f();
    }
}