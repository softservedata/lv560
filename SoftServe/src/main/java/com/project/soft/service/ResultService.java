package com.project.soft.service;

import com.project.soft.dao.ResultRepository;
import com.project.soft.entity.Result;
import com.project.soft.entity.Test;
import com.project.soft.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@RequiredArgsConstructor
@Service
public class ResultService {

    private final ResultRepository resultRepository;

    public void save(Result result) {
        if (result == null)
            throw new IllegalArgumentException("Result can't be null");

        resultRepository.save(result);
    }

    @Transactional(readOnly = true)
    public List<Result> findAllByUserAndTest(User user, Test test) {
        if (user == null || test == null )
            throw new IllegalArgumentException("User or Test can't be null");

        return resultRepository.findAllByUserAndTest(user, test);
    }

    @Transactional(readOnly = true)
    public Result findResultById(Long id) {
        return resultRepository.findById(id).orElseThrow(() ->
               new IllegalArgumentException(String.format("There's no result with id '%d'", id))
        );
    }
}
