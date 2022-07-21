package com.onyshkevych.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/register")
    public String getRegisterPage() {
        return "register";
    }

    @GetMapping("/quiz")
    public String getQuizPage() {
        return "redirect:register";
    }

    @GetMapping("/result")
    public String getResultPage() {
        return "redirect:register";
    }

    @GetMapping("/admin/users")
    public String getUsersPage() {
        return "redirect:/";
    }
    @GetMapping("/admin/create_quiz")
    public String getQuestionPage() {
        return "redirect:/";
    }

    @GetMapping("/admin/login")
    public String getManagerLoginPage() {
        return "loginmanager";
    }

    @GetMapping("/admin/createpage")
    public String getCreatePage() {
        return "redirect:/";
    }
    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }


    @GetMapping("/admin/users/checkres")
    public String getCheckResPage() {
        return "redirect:/";
    }
    @GetMapping("/admin/users/getuserinfo")
    public String getUserInfoPage() {
        return "redirect:/";
    }
    @GetMapping("/submitquiz")
    public String getSubmitQuizPage() {
        return "redirect:/";
    }
    @GetMapping("/getquiz")
    public String getQetQuizPage() {
        return "redirect:/";
    }




//    @GetMapping("/backtolist")
//    public String backToUserListPage() {
//        return "user_list";
//    }

}
