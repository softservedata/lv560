package com.project.soft.service;

import com.project.soft.dao.TestRepository;
import com.project.soft.entity.Test;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Transactional
@RequiredArgsConstructor
@Service
public class TestService {

    private final TestRepository testRepository;

    public void save(Test test) {
        if (test == null)
            throw new IllegalArgumentException("Test can't be null");

        testRepository.save(test);
    }

    public Test saveTest(Test test) {
        if (test == null)
            throw new IllegalArgumentException("Test can't be null");

        return testRepository.saveTest(test);
    }

    @Transactional(readOnly = true)
    public Set<Test> findTestsByTopic(Long topicId) {
        if (topicId == null)
            throw new IllegalArgumentException("Topic id can't be null");

        return testRepository.findTestsByTopic(topicId);
    }

    @Transactional(readOnly = true)
    public List<Test> findAll() {
        return testRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Test findTestById(Long id) {

        return testRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException(String.format("There's no test with id '%d'", id))
        );
    }

    @Transactional(readOnly = true)
    public List<Test> findUnarchivedTests() {
        return testRepository.findUnarchivedTests();
    }

    @Transactional(readOnly = true)
    public List<Test> findArchivedTests() {
        return testRepository.findArchivedTests();
    }

    @Transactional(readOnly = true)
    public List<Test> findALlByIds(List<Long> ids) {
        return testRepository.findALlByIds(ids);
    }
}
