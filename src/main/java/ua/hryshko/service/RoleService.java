package ua.hryshko.service;

import ua.hryshko.model.Role;

import java.util.List;

public interface RoleService {
    public void addRole(Role role);
    public List<Role> listRole();
    Role readById(Long id);
    public void removeRole(long id);
}
