package com.project.soft.service;

import com.project.soft.dao.QuestionTypeRepository;
import com.project.soft.entity.QuestionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class QuestionTypeService {

    private final QuestionTypeRepository questionTypeRepository;

    @Transactional(readOnly = true)
    public QuestionType findTypeByName(String name) {
        return questionTypeRepository.findTypeByName(name);
    }
}
