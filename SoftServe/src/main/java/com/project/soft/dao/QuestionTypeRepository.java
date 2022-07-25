package com.project.soft.dao;

import com.project.soft.dao.basedDao.SoftRepository;
import com.project.soft.entity.QuestionType;

public interface QuestionTypeRepository extends SoftRepository<QuestionType, Long> {

    QuestionType findTypeByName(String name);
}
