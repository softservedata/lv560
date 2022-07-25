package com.project.soft.service;

import com.project.soft.dao.CommentRepository;
import com.project.soft.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class CommentService {

    private final CommentRepository repository;

    public void save(Comment comment) {
        repository.save(comment);
    }

    public List<Comment> findAllByTestId(Long id) {
        return repository.findAllByTestId(id);
    }

    @Transactional(readOnly = true)
    public Comment findById(Long id) {
        if (id == null)
            throw new IllegalArgumentException("Id can't be null");

        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("There's no comment with id '%d'", id)
                ));
    }

    public void removeById(Long id) {
        if (id == null)
            throw new IllegalArgumentException("Id can't be null");

        repository.removeById(id);
    }
}
