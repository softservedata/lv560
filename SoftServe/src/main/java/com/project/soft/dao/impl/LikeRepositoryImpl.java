package com.project.soft.dao.impl;

import com.project.soft.dao.LikeRepository;
import com.project.soft.dao.basedDao.SimpleSoftRepository;
import com.project.soft.entity.Like;
import org.springframework.stereotype.Repository;

@Repository
public class LikeRepositoryImpl extends SimpleSoftRepository<Like, Long>
    implements LikeRepository {

    public LikeRepositoryImpl() {
        super(Like.class, Long.class);
    }
}
