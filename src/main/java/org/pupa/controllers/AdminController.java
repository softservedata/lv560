package org.pupa.controllers;

import org.pupa.models.PassedTest;
import org.pupa.models.Role;
import org.pupa.models.Test;
import org.pupa.models.User;
import org.pupa.services.QuestionService;
import org.pupa.services.RoleService;
import org.pupa.services.TestService;
import org.pupa.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;

@Controller
@SessionAttributes("user")
public class AdminController {
    private UserService userService;
    private TestService testService;
    private QuestionService questionService;
    private RoleService roleService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setTestService(TestService testService) {
        this.testService = testService;
    }

    @Autowired
    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String admin(){
        return "admin.html";
    }

    @GetMapping("/admin/users-list")
    public String userList(Model model){
        model.addAttribute("userList", userService.getAllUsers());
        return "users-list.html";
    }

    @GetMapping("/admin/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id,Model model) {
        User user = userService.findById(id).get();
        List<Role> roles = roleService.findAll();

        for (Role r: roles
        ) {
            System.out.println(r.getName() + " " + r.getId());
        }

        System.out.println("||");

        for (Role r: user.getRoles()
        ) {
            System.out.println(r.getName() + " " + r.getId());
        }

        model.addAttribute("listRoles",roles);
        model.addAttribute("user", user);
        return "update-user.html";
    }

    @PostMapping("/admin/update/")
    public String updateUser(@ModelAttribute("user") User user, Model model) {

        /*userService.updateUsernameAndRolesOfUser(user);*/
        HashSet<Role> newRoles = new HashSet<>();
        for (Role r: user.getRoles()
        ) {
            newRoles.add(roleService.findByName(r.getName()));
        }
        for (Role r: newRoles
             ) {
            System.out.println(r.getName() + " " + r.getId());
        }
        user.setRoles(newRoles);
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        User user = userService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userService.delete(user);
        return "redirect:/home";
    }

    @GetMapping("/admin/create-test")
    public String createTest(Model model) {
        return "tests/create-test.html";
    }

    @GetMapping("/admin/fill-test")
    public String fillTest(@RequestParam(value="name", required = true) String name,
                           @RequestParam(value="size") int size, Model model) {
        Test test = new Test(name,size);

/*        System.out.println(1);
        System.out.println(test.getName());
        for (Question q: test.getQuestions()
        ) {
            System.out.println(q);
        }*/
        model.addAttribute("test",test);
        return "tests/fill-test.html";
    }

    @PostMapping("/admin/save-test")
    public String saveTest(@ModelAttribute("test") Test test, Model model) {
        if(test == null){
            throw new RuntimeException("Test is null!");
        }

/*        System.out.println(2);
        System.out.println(test.getName());
        for (Question q: test.getQuestions()
             ) {
            System.out.println(q);
        }*/
        testService.saveTest(test);
        return "redirect:/home";
    }

}
