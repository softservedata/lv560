package com.project.soft.dao;

import com.project.soft.dao.basedDao.SoftRepository;
import com.project.soft.entity.Question;
import com.project.soft.entity.Test;

import java.util.List;

public interface QuestionRepository extends SoftRepository<Question, Long> {

    List<Question> findQuestionsByTest(Test test);
    List<Question> findAllByIds(Long[] questionIds);
}
