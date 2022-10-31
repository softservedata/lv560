package org.pupa.services;

import org.pupa.models.Question;
import org.pupa.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    private QuestionRepository questionRepository;

    @Autowired
    public void setQuestionRepository(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public void saveAll(List<Question> questionList){
        questionRepository.saveAll(questionList);
    }
}
