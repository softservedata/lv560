package com.softserve.edu08;

public class B extends A {
    public int j = 321; // Achitecture (Encapsulation) Error
    public int i = 555; // Achitecture Error

    public B() {
        // super();
        //super(444);
        System.out.println("constructor B()");
    }

    public B(int j) {
        // super();
        super(888);
        this.j = j;
        System.out.println("constructor B(int j)");
    }

    // /*-
    @Override
    public int getI() {
    	System.out.println("\tgetI() from B");
    	//return i;
    	return super.getI();
    }
    // */

    public int getI1() {
        System.out.println("\tgetI1() from B");
        return super.getI();
    }
}
