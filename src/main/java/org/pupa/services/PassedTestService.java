package org.pupa.services;

import org.hibernate.Hibernate;
import org.pupa.models.PassedTest;
import org.pupa.repositories.PassedTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PassedTestService {
    private PassedTestRepository testRepository;

    @Autowired
    public void setTestRepository(PassedTestRepository testRepository) {
        this.testRepository = testRepository;
    }

    public void save(PassedTest passedTest){
        testRepository.save(passedTest);
    }

    @Transactional
    public PassedTest findById(long id){
        PassedTest passedTest = testRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid test Id:" + id));
        Hibernate.initialize(passedTest.getTest());
        Hibernate.initialize(passedTest.getTest().getQuestions());
        return passedTest;
    }

}
