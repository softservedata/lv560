package com.softserve.edu;

import com.softserve.edu.internal.KnowledgeHelper;

public class Knowledge {

    public String obtain(String requirements) {
        String knowledge = KnowledgeHelper.obtainDomainKnowledge(requirements);
        System.out.println("Now I know everything");
        System.out.println(knowledge);
        return knowledge;
    }

}
