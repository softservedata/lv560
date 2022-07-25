package com.project.soft.util;

import com.project.soft.entity.Question;
import com.project.soft.entity.Test;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TestValidator {

    public static void validateAnswers(List<String> answerTitles,
                                 String[] correct,
                                 BindingResult errors) {

        boolean hasEmpty = false;
        Set<String> answerTitlesSet = new HashSet<>(answerTitles);

        if (correct == null) {
            FieldError error = new FieldError(
                    "questionContainer",
                    "answers",
                    "There must be at least one correct answer"
            );
            errors.addError(error);
        }

        for (String title : answerTitles) {
            if (title.isEmpty()) {
                hasEmpty = true;
                break;
            }
        }
        if (hasEmpty) {
            FieldError error = new FieldError(
                    "questionContainer",
                    "answers",
                    "Answers can't be empty"
            );
            errors.addError(error);
        }

        if (answerTitlesSet.size() != answerTitles.size()) {
            FieldError error = new FieldError(
                    "questionContainer",
                    "answers",
                    "There are duplicate answers"
            );
            errors.addError(error);
        }
    }

    public static void validateQuestion(Test test,
                                        String prevTitle,
                                        String questionTitle,
                                        Long categoryId,
                                        BindingResult errors) {
        boolean hasError = false;

        List<String> questionTitles = test.getQuestions().stream()
                .map(Question::getTitle)
                .collect(Collectors.toList());

        if (prevTitle == null) {
            hasError = hasDuplicates(questionTitle, questionTitles);
        } else {
            if (!prevTitle.equals(questionTitle)) {
                hasError = hasDuplicates(questionTitle, questionTitles);
            }
        }

        if (categoryId == null) {
            FieldError error = new FieldError(
                    "updateContainer",
                    "category",
                    "You must choose category"
            );
            errors.addError(error);
        }

        if (hasError) {
            FieldError error = new FieldError(
                    "updateContainer",
                    "questionTitle",
                    "Question with this title already exists in the test"
            );
            errors.addError(error);
        }
    }

    public static boolean hasDuplicates(String questionTitle, List<String> questionTitles) {
        boolean hasDuplicate = false;

        for (String title : questionTitles) {
            if (title.equals(questionTitle)) {
                hasDuplicate = true;
                break;
            }
        }
        return hasDuplicate;
    }

    public static void validateTest(String testName, Test test, BindingResult errors) {
        if (test.getQuestions().size() < 4) {
            FieldError error = new FieldError(
                    "questionContainer",
                    "test",
                    "Test must contain at least 4 questions"
            );
            errors.addError(error);
        }

        if (testName.length() < 5 || testName.length() < 40) {
            FieldError error = new FieldError(
                    "questionContainer",
                    "testName",
                    "Name should be between 5 and 50 characters"
            );
            errors.addError(error);
        }
    }

    public static boolean isValidTest(String testName, Test test) {
        boolean isValid = true;

        if (testName.length() < 5 || testName.length() > 40)
            isValid = false;

        if (test.getQuestions().size() < 4)
            isValid = false;

        return isValid;
    }

    public static void validateQuestion(List<String> answerTitles,
                                  String[] correct,
                                  Integer value,
                                  String questionTitle,
                                  Long categoryId,
                                  BindingResult errors) {


        if (categoryId == null) {
            FieldError error = new FieldError(
                    "updateContainer",
                    "category",
                    "You must choose category"
            );
            errors.addError(error);
        }

        if (questionTitle.isEmpty()) {
            FieldError error = new FieldError(
                    "updateContainer",
                    "questionTitle",
                    "This field can't be empty"
            );
            errors.addError(error);
        }

        if (value == null) {
            FieldError error = new FieldError(
                    "updateContainer",
                    "value",
                    "This field can't be empty"
            );
            errors.addError(error);
        } else if (value < 1) {
            FieldError error = new FieldError(
                    "updateContainer",
                    "value",
                    "Minimal value of question should be equals or greater than 1"
            );
            errors.addError(error);
        }

        validateAnswers(answerTitles, correct, errors);
    }
}
