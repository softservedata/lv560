package com.project.soft.util;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum CompletedQuestionStatus {

    CORRECT("Your answer is correct"),
    INCORRECT("Your answer is incorrect"),
    PARTIALLY_CORRECT("Your answer is partially correct");

    private final String status;

    @Override
    public String toString() {
        return status;
    }
}
