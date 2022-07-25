package com.project.soft.dao;

import com.project.soft.dao.basedDao.SoftRepository;
import com.project.soft.entity.Test;
import com.project.soft.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends SoftRepository<User, Long> {
    Optional<User> findByUsername(String username);
    List<User> findUsersByTest(Test test);
}
