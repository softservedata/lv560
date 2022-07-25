package com.project.soft.dao.impl;

import com.project.soft.dao.CategoryRepository;
import com.project.soft.dao.basedDao.SimpleSoftRepository;
import com.project.soft.entity.Category;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryRepositoryImpl extends SimpleSoftRepository<Category, Long>
    implements CategoryRepository {

    public CategoryRepositoryImpl() {
        super(Category.class, Long.class);
    }
}
