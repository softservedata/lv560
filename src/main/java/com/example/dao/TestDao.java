package com.example.dao;

import com.example.model.Question;
import com.example.model.Test;

import java.util.List;

public interface TestDao {

    /**
     * @param id - id that will be inserted into query
     * @return Test object
     */
    Test findById(Integer id);

    /**
     * @return List of all tests found
     */
    List<Test> getAllTests();

    /**
     * saves test into DB
     * @param test - Test to save
     */
    void save(Test test);

    /**
     * @param id - Test id to find questions
     * @return List of questions of a concrete test
     */
    List<Question> getQuestionsByTest(Integer id);

    /**
     * @param test - test to update
     */
    void update(Test test);

    /**
     * @param test - test to delete
     */
    void delete(Test test);
}
