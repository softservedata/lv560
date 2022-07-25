package com.project.soft.util;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class OutputResultData {

    private List<OutputAnswerContainer> outputAnswerContainers = new ArrayList<>();

    public void add(OutputAnswerContainer container) {
        outputAnswerContainers.add(container);
    }
}
