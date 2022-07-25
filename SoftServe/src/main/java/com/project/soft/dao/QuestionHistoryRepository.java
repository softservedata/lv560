package com.project.soft.dao;

import com.project.soft.dao.basedDao.SoftRepository;
import com.project.soft.entity.QuestionHistory;
import com.project.soft.entity.Result;

import java.util.List;

public interface QuestionHistoryRepository extends SoftRepository<QuestionHistory, Long> {

    List<QuestionHistory> findHistoriesByResult(Result result);
}
