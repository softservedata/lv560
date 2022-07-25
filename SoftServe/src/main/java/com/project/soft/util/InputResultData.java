package com.project.soft.util;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class InputResultData {

    private List<InputAnswersContainer> inputAnswersContainers = new ArrayList<>();

    public void addContainer(InputAnswersContainer inputAnswersContainer) {
        Long id = inputAnswersContainer.getQuestionId();

        for (InputAnswersContainer container : inputAnswersContainers) {
            if (container.getQuestionId().equals(id)) {
                container.setAnswerId(inputAnswersContainer.getAnswerId());
                container.setAnswerIds(inputAnswersContainer.getAnswerIds());
                container.setQuestionType(inputAnswersContainer.getQuestionType());
                container.setQuestionId(inputAnswersContainer.getQuestionId());

                return;
            }
        }

        inputAnswersContainers.add(inputAnswersContainer);
    }
}
