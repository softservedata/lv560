package com.softserve.traning;

@FunctionalInterface
public interface One {
    void m1();
    //void m2();
    boolean equals(Object obj);
    int hashCode();
    @Override
    String toString();
//    {
//        return "One";
//    }
}
