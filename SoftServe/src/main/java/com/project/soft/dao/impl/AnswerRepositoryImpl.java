package com.project.soft.dao.impl;

import com.project.soft.dao.AnswerRepository;
import com.project.soft.dao.basedDao.SimpleSoftRepository;
import com.project.soft.entity.Answer;
import org.springframework.stereotype.Repository;


@Repository
public class AnswerRepositoryImpl extends SimpleSoftRepository<Answer, Long>
    implements AnswerRepository {

    public AnswerRepositoryImpl() {
        super(Answer.class, Long.class);
    }
}