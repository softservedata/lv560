package com.softserve.edu.service;

import com.softserve.edu.Knowledge;
// import com.softserve.edu.internal.KnowledgeHelper; // Error

public class Owner {

    public static void main(String[] args) {
        Knowledge knowledge = new Knowledge();
        SoftwareDeveloper developer = new SoftwareDeveloper(knowledge);
        String software = developer.develop("I want it to be this way and that way");
        System.out.println(software);
        //
    }

}
