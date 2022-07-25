package com.project.soft.util;

import com.project.soft.entity.Category;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class InputQuestionContainer {

    @NotBlank(message = "This field can't be empty")
    private String questionTitle;
    private List<String> answerTitles = new ArrayList<>();
    private Category category;
    @NotNull(message = "This field can't be empty")
    @Min(value = 1, message = "Minimal value of question should be equals or greater than 1")
    private Integer value;
}