package com.project.soft.dao.impl;

import com.project.soft.dao.GroupRepository;
import com.project.soft.dao.basedDao.SimpleSoftRepository;
import com.project.soft.entity.Group;
import org.springframework.stereotype.Repository;

@Repository
public class GroupRepositoryImpl extends SimpleSoftRepository<Group, Long>
    implements GroupRepository {

    public GroupRepositoryImpl() {
        super(Group.class, Long.class);
    }
}
