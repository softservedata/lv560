package com.hehetenya.test_forms.service;

import com.hehetenya.test_forms.dao.impl.DaoFactory;
import com.hehetenya.test_forms.dto.UserDTO;
import com.hehetenya.test_forms.entity.Role;
import com.hehetenya.test_forms.entity.User;

import java.util.List;

public class UserService {

    public static UserDTO login(UserDTO userDTO) {
        List<User> users = DaoFactory.getUserDao().getAll();
        for (User u: users) {
            if(u.getLogin().equals(userDTO.getLogin())
                    && u.getPassword().equals(userDTO.getPassword())){
                userDTO.setRole(u.getRole());
                return userDTO;
            }
        }
        return null;
    }

    public static UserDTO register(UserDTO user) {
        DaoFactory.getUserDao().create(new User(0, user.getLogin(), user.getPassword(), Role.USER));
        return login(user);
    }

    public static UserDTO transform(User user) {
        return new UserDTO(user.getLogin(), user.getPassword(), user.getRole());
    }

    public static User transformDTO(UserDTO user) {
        List<User> users = DaoFactory.getUserDao().getAll();
        for(User u: users){
            if(u.getLogin().equals(user.getLogin())){
                return u;
            }
        }
        return null;

    }
}
