package com.project.soft.service;

import com.project.soft.dao.CategoryRepository;
import com.project.soft.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class CategoryService {

    private final CategoryRepository repository;

    public void save(Category category) {
        if (category == null)
            throw new IllegalArgumentException("Category can't be null");

        repository.save(category);
    }

    public Category findById(Long id) {
        if (id == null)
            throw new IllegalArgumentException("Category id can't be null");

        return repository.findById(id).orElseThrow(() ->
                new IllegalArgumentException(String.format("There's no category with id '%d'", id))
        );
    }

    public List<Category> findAllCategories() {
        return repository.findAll();
    }

    public void removeById(Long id){
        if (id == null)
            throw new IllegalArgumentException("Category id can't be null");

        repository.removeById(id);
    }
}
