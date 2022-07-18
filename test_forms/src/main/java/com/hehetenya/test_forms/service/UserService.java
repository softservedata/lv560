package com.hehetenya.test_forms.service;

import com.hehetenya.test_forms.dao.impl.DaoFactory;
import com.hehetenya.test_forms.dto.UserDTO;
import com.hehetenya.test_forms.entity.Role;
import com.hehetenya.test_forms.entity.User;
import com.hehetenya.test_forms.exeptions.AppException;
import com.hehetenya.test_forms.exeptions.DBException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UserService {

    public static UserDTO login(UserDTO userDTO) {
        try {
            List<User> users = DaoFactory.getUserDao().getAll();
            for (User u : users) {
                if (u.getLogin().equals(userDTO.getLogin())
                        && u.getPassword().equals(userDTO.getPassword())) {
                    userDTO.setRole(u.getRole());
                    return userDTO;
                }
            }
        }catch (DBException e){
            throw new AppException();
        }
        return null;
    }

    public static void register(HttpServletRequest request, HttpServletResponse response) {
        try{
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            if (login.isEmpty() || password.isEmpty()){
                response.sendRedirect(request.getContextPath() + "/login");
            }
            UserDTO user = new UserDTO(login, password);
            if(UserService.login(user) != null){
                request.setAttribute("error", "true");
                response.sendRedirect(request.getContextPath() + "/register?err=");
            } else{
                DaoFactory.getUserDao().create(new User(0, user.getLogin(), user.getPassword(), Role.USER));
                user = login(user);
                request.getSession().setAttribute("user", user);
                response.sendRedirect(request.getContextPath() + "/home");
            }
        }catch (Exception e){
            throw new AppException();
        }
    }

    public static UserDTO transform(User user) {
        return new UserDTO(user.getLogin(), user.getPassword(), user.getRole());
    }

    public static User transformDTO(UserDTO user) {
        try {
            List<User> users = DaoFactory.getUserDao().getAll();
            for (User u : users) {
                if (u.getLogin().equals(user.getLogin())) {
                    return u;
                }
            }
            return null;
        }catch (DBException e){
            throw new AppException();
        }
    }

    public static void tryToLogIn(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        if (login.isEmpty() || password.isEmpty()){
            response.sendRedirect(request.getContextPath() + "/login");
        }
        UserDTO user = new UserDTO(login, password);
        user = UserService.login(user);
        if(user == null){
            request.setAttribute("error", "true");
            response.sendRedirect(request.getContextPath() + "/login?err=");
        }
        request.getSession().setAttribute("user", user);
        response.sendRedirect(request.getContextPath() + "/home");
    }
}
