package ua.hryshko.dao;

import ua.hryshko.model.Role;

import java.util.List;

public interface RoleDAO {
    void addRole(Role role);
    List<Role> listRole();
    Role readById(Long id);
    void removeRole(Long id);
}
