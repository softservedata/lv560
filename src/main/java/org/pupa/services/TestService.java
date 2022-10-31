package org.pupa.services;

import org.hibernate.Hibernate;
import org.pupa.models.Test;
import org.pupa.repositories.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TestService {
    private TestRepository testRepository;

    @Autowired
    public void setTestRepository(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    public void saveTest(Test test){
        testRepository.save(test);
    }

    @Transactional
    public List<Test> findAll(){
        List<Test> tests = testRepository.findAll();
        for (Test t: tests
             ) {
            Hibernate.initialize(t.getQuestions());
        }
        return tests;
    }

    @Transactional
    public Test findById(long id){
        Test test = testRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid test Id:" + id));
        Hibernate.initialize(test.getQuestions());
        return test;
    }
}
