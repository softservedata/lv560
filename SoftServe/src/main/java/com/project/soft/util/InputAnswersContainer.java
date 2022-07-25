package com.project.soft.util;

import com.project.soft.entity.QuestionType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import static com.project.soft.config.ApplicationParameters.MAX_VARIANTS;

@Getter
@Setter
public class InputAnswersContainer {

    private Long questionId;
    private QuestionType questionType;
    private Long answerId;
    @Setter(AccessLevel.PACKAGE)
    private Long[] answerIds = new Long[MAX_VARIANTS];

    int index = 0;

    public void addId(Long id) {
        answerIds[index++] = id;
    }
}
