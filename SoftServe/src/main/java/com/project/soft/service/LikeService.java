package com.project.soft.service;

import com.project.soft.dao.LikeRepository;
import com.project.soft.entity.Like;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class LikeService {

    private final LikeRepository repository;

    public void save(Like like) {
        repository.save(like);
    }

    public void removeById(Long likeId) {
        repository.removeById(likeId);
    }
}