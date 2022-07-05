package com.example.service;

import com.example.dao.TestDao;
import com.example.model.Question;
import com.example.model.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TestService {

    private TestDao testDao;

    @Autowired
    public TestService(TestDao testDao){this.testDao = testDao; }

    public List<Test> getAllTests(){
        return testDao.getAllTests();
    }

    public Test getTestById(Integer id){
        return testDao.findById(id);
    }

    public void saveTest(Test test){
        testDao.save(test);
    }

    public void updateTest(Test test){
        testDao.update(test);
    }

    public void deleteTest(Test test){
        testDao.delete(test);
    }

    public List<Question> getQuestionsByTestId(Integer id){
        return testDao.getQuestionsByTest(id);
    }
}
