package com.project.soft.dao;

import com.project.soft.dao.basedDao.SoftRepository;
import com.project.soft.entity.Comment;

import java.util.List;

public interface CommentRepository extends SoftRepository<Comment, Long> {

    List<Comment> findAllByTestId(Long id);
}
