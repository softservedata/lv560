package org.pupa.controllers;

import org.pupa.models.*;
import org.pupa.services.PassedTestService;
import org.pupa.services.TestService;
import org.pupa.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("passedTest")
public class TestsController {
    private TestService testService;
    private PassedTestService passedTestService;
    private UserService userService;

    @Autowired
    public void setTestService(TestService testService) {
        this.testService = testService;
    }

    @Autowired
    public void setPassedTestService(PassedTestService passedTestService) {
        this.passedTestService = passedTestService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all-tests")
    public String allTests(Model model, Principal principal) {
        List<Test> tests = testService.findAll();
        model.addAttribute("tests", tests);
        model.addAttribute("isManager", userService.findByUsername(principal.getName()).isManager());
        return "tests/all-tests.html";
    }

    @GetMapping("/pass-test/{id}")
    public String passTest(@PathVariable("id") long id ,Model model) {
        Test test = testService.findById(id);
        PassedTest passedTest = new PassedTest();
        passedTest.setTest(test);
        ArrayList<Answer> answers = new ArrayList<>(test.getAmountOfQuestions());
        for(int i = 0; i < test.getAmountOfQuestions(); i++){
            answers.add(new Answer());
        }
        passedTest.setAnswers(answers);

/*
        System.out.println(passedTest.getTest().getName());
        System.out.println(passedTest.getTest().getId());
        for (Question q: passedTest.getTest().getQuestions()
        ) {
            System.out.println(q);
        }
        for (Answer a: passedTest.getAnswers()
        ) {
            System.out.println(a);
        }
*/

        model.addAttribute("passedTest", passedTest);
        return "tests/pass-test.html";
    }

    @PostMapping("/save-test")
    public String passTest(@ModelAttribute(name = "passedTest") PassedTest passedTest,
                           Model model,
                           Principal principal) {
        if(passedTest == null){
            throw new RuntimeException("Test isn't present!");
        }
        User currentUser = userService.findByUsername(principal.getName());

        currentUser.addPassedTest(passedTest);
        userService.save(currentUser);

/*        System.out.println(passedTest.getTest().getName());
        System.out.println(passedTest.getTest().getId());
        for (Question q: passedTest.getTest().getQuestions()
        ) {
            System.out.println(q);
        }
        for (Answer a: passedTest.getAnswers()
        ) {
            System.out.println(a);
        }*/

        return "redirect:/home";
    }

    @GetMapping("/my-tests")
    public String userTests(Model model, Principal principal) {
        User currentUser = userService.findByUsername(principal.getName());
        List<PassedTest> passedTests = currentUser.getPassedTests();
        model.addAttribute("passedTests", passedTests);
        model.addAttribute("isManager", currentUser.isManager());
        return "tests/my-tests.html";
    }

    @GetMapping("/my-tests/view/{id}")
    public String viewResultOfTest(@PathVariable("id") long id, Model model, Principal principal) {
        PassedTest passedTest = passedTestService.findById(id);
        model.addAttribute("passedTest", passedTest);
        model.addAttribute("isManager", userService.findByUsername(principal.getName()).isManager());
        return "tests/view-result.html";
    }

}
