package com.softserve.edu.service;

import com.softserve.edu.Knowledge;

public class SoftwareDeveloper {
    private Knowledge knowledge;

    public SoftwareDeveloper(Knowledge knowledge) {
        this.knowledge = knowledge;
    }

    public String develop(String requirements) {
        System.out.println(requirements);
        System.out.println("And the requirements...");
        //
        knowledge.obtain(requirements);
        //
        return " This is the software";
    }

}
