package com.example.service;

import com.example.dao.ResultDao;
import com.example.dao.TestDao;
import com.example.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ResultService {

    private ResultDao resultDao;

    @Autowired
    public ResultService(ResultDao resultDao){this.resultDao = resultDao; }

    public List<Result> findAllResults(){
        return resultDao.findAllResults();
    }

    public List<Result> findAllResultsByUserId(Integer id){
        return resultDao.findAllResultsByUserId(id);
    }

    public List<Result> findResultsByTestId(Integer id){
        return resultDao.findResultsByTestId(id);
    }

    public Result findResultByUserAndTestId(Integer userId, Integer testId){
        return resultDao.findResultByUserAndTestId(userId, testId);
    }

    public void save(Result result){
        resultDao.save(result);
    }

    public void update(Result result){
        resultDao.update(result);
    }

    public void delete(Result result){
        resultDao.delete(result);
    }
}
