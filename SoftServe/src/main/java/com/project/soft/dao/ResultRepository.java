package com.project.soft.dao;

import com.project.soft.dao.basedDao.SoftRepository;
import com.project.soft.entity.Result;
import com.project.soft.entity.Test;
import com.project.soft.entity.User;

import java.util.List;

public interface ResultRepository extends SoftRepository<Result, Long> {

    List<Result> findAllByUserAndTest(User user, Test test);
}
