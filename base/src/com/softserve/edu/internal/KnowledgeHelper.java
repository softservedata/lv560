package com.softserve.edu.internal;

import java.util.Random;

public class KnowledgeHelper {

    private static final Random random = new Random();
    private static final String bankArea = "The banking sector, I'm talking about you";
    private static final String stockExchangeArea = "Well, it's a stock exchange";

    public static String obtainDomainKnowledge(String requirements) {
        return Math.random() > 0.5 ? bankArea : stockExchangeArea;
    }

}
