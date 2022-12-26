package ua.hryshko.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.hryshko.dao.RoleDAO;
import ua.hryshko.model.Role;
import ua.hryshko.service.RoleService;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleDAO roleDAO;

    @Transactional
    public void addRole(Role role) {
        roleDAO.addRole(role);
    }

    @Transactional
    public List<Role> listRole() {
        return roleDAO.listRole();
    }

    @Transactional
    public Role readById(Long id) {
        return roleDAO.readById(id);
    }

    @Transactional
    public void removeRole(long id) {
        roleDAO.removeRole(id);

    }
}
