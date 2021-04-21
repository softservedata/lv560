package com.softserve.edu.pchild;

import com.softserve.edu.parent.Parent;

public class Child extends Parent {
    
    //@Override
    public int f() {
    //protected int f() {
        return 2;
    }
    
    public int useF2() {
        return useF();
    }
}

