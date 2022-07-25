package com.project.soft.service;

import com.project.soft.dao.QuestionRepository;
import com.project.soft.entity.Question;
import com.project.soft.entity.Test;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public void save(Question question) {
        if (question == null)
            throw new IllegalArgumentException("Question can't be null");

        questionRepository.save(question);
    }

    @Transactional(readOnly = true)
    public List<Question> findQuestionsByTest(Test test) {
        if (test == null)
            throw new IllegalArgumentException("Test can't be null");

        return questionRepository.findQuestionsByTest(test);
    }

    @Transactional(readOnly = true)
    public List<Question> findAllByIds(Long[] questionIds) {

        return questionRepository.findAllByIds(questionIds);
    }
}
