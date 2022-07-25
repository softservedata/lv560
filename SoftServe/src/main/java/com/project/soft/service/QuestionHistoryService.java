package com.project.soft.service;

import com.project.soft.dao.QuestionHistoryRepository;
import com.project.soft.entity.QuestionHistory;
import com.project.soft.entity.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class QuestionHistoryService {

    private final QuestionHistoryRepository historyRepository;

    @Transactional(readOnly = true)
    public List<QuestionHistory> findHistoriesByResult(Result result) {
        if (result == null)
            throw new IllegalArgumentException("Result can't be null");

        return historyRepository.findHistoriesByResult(result);
    }
}
