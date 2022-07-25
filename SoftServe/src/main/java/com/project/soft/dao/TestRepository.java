package com.project.soft.dao;

import com.project.soft.dao.basedDao.SoftRepository;
import com.project.soft.entity.Test;

import java.util.List;
import java.util.Set;

public interface TestRepository extends SoftRepository<Test, Long> {

    List<Test> findUnarchivedTests();
    List<Test> findArchivedTests();
    Set<Test> findTestsByTopic(Long topicId);
    Test saveTest(Test test);
}
