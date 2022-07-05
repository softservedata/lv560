package com.example.dao;

import com.example.model.Result;

import java.util.List;

public interface ResultDao {

    /**
     * @return List of all results
     */
    List<Result> findAllResults();

    /**
     *
     * @param id - id of user to find results
     * @return
     */
    List<Result> findAllResultsByUserId(Integer id);

    List<Result> findResultsByTestId(Integer id);
    /**
     * @param userId - id of user
     * @param testId - id of test
     * @return Result object - result of a concrete test
     */
    Result findResultByUserAndTestId(Integer userId, Integer testId);

    /**
     * @param result - result to save
     */
    void save(Result result);

    /**
     * @param result - result to update
     */
    void update(Result result);

    /**
     * @param result - result to delete
     */
    void delete(Result result);
}
