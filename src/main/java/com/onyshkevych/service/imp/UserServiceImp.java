package com.onyshkevych.service.imp;

import com.onyshkevych.dao.UserDAO;
import com.onyshkevych.model.User;
import com.onyshkevych.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Objects;
@Service
public class UserServiceImp implements UserService {
    private final UserDAO uRepo;

    @Autowired
    public UserServiceImp(UserDAO uRepo) {
        this.uRepo = uRepo;
    }

    @Transactional
    @Override
    public String saveUser(User user) {
        uRepo.saveUser(user);
        return "redirect:/";
    }

    @Transactional
    @Override
    public List<User> getAllUsers() {
        return  uRepo.getAllUsers();
    }


    @Transactional
    @Override
    public String loginUser(String name, String password) {
        if (isUserLogined(name, password)) {
            System.out.println(name);
            return "redirect:/seequiz";
        } else {
            return "redirect:login";
        }
    }
    @Transactional
    @Override
    public User getUserByName(String name) {
    return uRepo.getUserByName(name);
    }
    @Transactional
    @Override
    public User getUserById(Integer user_id) {
        return uRepo.getUserById(user_id);
    }
    @Transactional
    @Override
    public boolean isUserLogined(String name, String pass) {
        List<User> allUsers = uRepo.getAllUsers();
        boolean userLogin = false;
        for (User user : allUsers) {
            if (Objects.equals(user.getName(), name)
            &&(Objects.equals(user.getPassword(), pass))
            ) {
                userLogin = true;
                break;
            }

        }
        return userLogin;

    }

    public boolean ifUserNameIsInUse(String userName, List<User> allUsers) {
        boolean isUserNameInUse = false;
        for (User user : allUsers) {
            if (Objects.equals(user.getName(), userName)) {
                isUserNameInUse = true;
                break;
            }

        }
        return isUserNameInUse;
    }
    public boolean ifUserPassIsNormall(String userPass) {
        return userPass.length() >= 8;
    }
    public void saveUser(String userName, String userPass){
        List<User> allUsers = uRepo.getAllUsers();
        int user_role = 0;
        User newUser = new User(allUsers.size()+1, userName, userPass, user_role);
        uRepo.saveUser(newUser);
    }

    public boolean isUserNotRegistered(String userName, String userPass) {
        List<User> allUsers = uRepo.getAllUsers();
        return !(ifUserNameIsInUse(userName, allUsers)) && ifUserPassIsNormall(userPass);
    }

    @Transactional
    @Override
    public String registerUser(String username, String userpass) {
        if(username.equals("")) {
            return "redirect:/register";
        } else if (isUserNotRegistered(username, userpass)) {
            saveUser(username, userpass);
            return "login";
        }
        return "redirect:/register";
    }

    public User getUserByRole(Integer user_role) {
        return uRepo.getUserByRole(user_role);
    }



    public boolean isManagerLogined(String name, String pass) {
        List<User> managers = uRepo.getAllUsers();
        int manager_role = 1;
        boolean isManagerLogin = false;
        for (User manager : managers) {
            if (Objects.equals(manager.getName(), name)
                    && Objects.equals(manager.getPassword(), pass)
                    && Objects.equals(manager.getUser_role(), manager_role)
            ) {
                isManagerLogin = true;
                break;
            }

        }
        return isManagerLogin;

    }

    @Transactional
    @Override
    public String loginManager(String manager_name, String manager_pass) {
        if (isManagerLogined(manager_name, manager_pass)) {
            return "adminpage";
        } else {
            return "redirect:/admin/login";
        }
    }
    @Transactional
    @Override
    public String getUserInfo(Integer user_id, Model m) {
        User user = getUserById(user_id);
        m.addAttribute("user", user);
        System.out.println(user);
        return "user_details";
    }
}
