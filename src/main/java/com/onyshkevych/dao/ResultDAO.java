package com.onyshkevych.dao;

import com.onyshkevych.model.Result;

import java.util.List;

public interface ResultDAO {
    /**
     * Getting all results from db.
     *
     */
    List<Result> getAllResults();
    /**
     * Saving Result in db
     *  @param  result   result entity
     */
    void saveResult(Result result);
}
