package com.project.soft.util;

import com.project.soft.entity.Answer;
import com.project.soft.entity.Question;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class OutputAnswerContainer {

    private Question question;
    private CompletedQuestionStatus status;
    private int value;
    @Setter(AccessLevel.PRIVATE)
    private Map<Answer, Boolean> answerDetailsMap = new HashMap<>();

    public void put(Answer answer, Boolean selected) {
        answerDetailsMap.put(answer, selected);
    }
}
