package com.softserve.edu;

import com.softserve.edu.internal.KnowledgeHelper;
import com.softserve.edu.service.SoftwareDeveloper; // Ok

public class Appl {

    public static void main(String[] args) {
        Knowledge knowledge = new Knowledge();
        SoftwareDeveloper developer = new SoftwareDeveloper(knowledge);
        String software = developer.develop("I want it to be this way and that way");
        System.out.println(software);
        //
        KnowledgeHelper k;
        //
        System.out.println("done");
    }

}
