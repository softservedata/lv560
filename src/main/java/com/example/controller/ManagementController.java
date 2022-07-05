package com.example.controller;

import com.example.model.Test;
import com.example.model.User;
import com.example.service.TestService;
import com.example.service.UserService;
import com.example.util.Role;
import com.example.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@PreAuthorize("hasAuthority('user:write')")
@RequestMapping("/management")
public class ManagementController {

    private final UserService userService;
    private final TestService testService;

    @Autowired
    public ManagementController(TestService testService, UserService userService) {
        this.userService = userService;
        this.testService = testService;
    }

    @PreAuthorize("hasAuthority('user:write')")
    @GetMapping("/manageUsers")
    public String manageUsers(Model model){
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "manageUsers";
    }

    @PreAuthorize("hasAuthority('user:write')")
    @GetMapping("/manageTests")
    public String manageTests(Model model){
        List<Test> tests = testService.getAllTests();
        model.addAttribute("tests", tests);
        return "manageTests";
    }

    @PreAuthorize("hasAuthority('user:write')")
    @GetMapping("/")
    public String management(Model model){
        return "managementPage";
    }

    @PreAuthorize("hasAuthority('user:write')")
    @GetMapping("/editUser/{id}")
    public String editUser(@PathVariable("id")Integer id, Model model){
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "editUser";
    }

    @PreAuthorize("hasAuthority('user:write')")
    @PostMapping("/editUser/{id}")
    public String editUser(@PathVariable("id")Integer id,
                           @RequestParam("username")String username,
                           @RequestParam("full_name")String fullName,
                           @RequestParam("password")String password,
                           @RequestParam("email")String email,
                           @RequestParam("role")String role,
                           Model model){
            Role userRole;

            if (role.equals("MANAGER")){
                userRole = Role.MANAGER;
            }else{
                userRole = Role.USER;
            }

            User user = User.builder()
                    .id(id)
                    .username(username)
                    .password(password)
                    .fullName(fullName)
                    .email(email)
                    .status(Status.ACTIVE)
                    .role(userRole)
                    .build();

            userService.updateUser(user);
        return "redirect:/management/manageUsers";
    }

    @PreAuthorize("hasAuthority('user:write')")
    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id")Integer id){
        User user = userService.findById(id);
        userService.deleteUser(user);
        return "redirect:/management/manageUsers";
    }

    @PreAuthorize("hasAuthority('user:write')")
    @GetMapping("/editTest/{id}")
    public String editTest(@PathVariable("id")Integer id, Model model){
        Test test = testService.getTestById(id);
        model.addAttribute("test", test);
        return "editTest";
    }

    @PreAuthorize("hasAuthority('user:write')")
    @PostMapping("/editTest/{id}")
    public String editTest(@PathVariable("id")Integer id, @RequestBody Test test, Model model){
        testService.updateTest(test);
        return "editTest";
    }
}
