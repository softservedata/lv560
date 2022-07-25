package com.project.soft.dao;

import com.project.soft.dao.basedDao.SoftRepository;
import com.project.soft.entity.Role;

public interface RoleRepository extends SoftRepository<Role, Long> {

    Role findRoleByName(String name);
}
