package com.onyshkevych.controller;

import com.onyshkevych.model.QuestionForm;
import com.onyshkevych.model.Result;
import com.onyshkevych.service.QuestionService;
import com.onyshkevych.service.ResultService;
import com.onyshkevych.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class QuizController {
    Boolean submitted = false;
    @Autowired
    Result result;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private UserService userService;

    @Autowired
    private ResultService resultService;

    @ModelAttribute("result")
    public Result getResult() {
        return result;
    }

    @PostMapping("/quiz")
    public String quiz(@RequestParam String name, @RequestParam String password, Model m) {
        if (userService.isUserLogined(name, password)) {
            return questionService.startQuiz(name, password,  result, m,  submitted);
        }
        return "redirect:register";
    }

    @PostMapping("/submit")
    public String submit(@ModelAttribute QuestionForm qForm, Model m) {
        return resultService.submitResult(qForm, m,  result, submitted);
    }

    @GetMapping("/score")
    public String score(Model m) {
        return  resultService.score(m);

    }
    @PostMapping("/admin/create_que")
    public String getCreatePage() {
        return "question_creation";
    }


    @PostMapping("/admin/create")
    public String createQuestion(@RequestParam String title,
                                 @RequestParam String optionA,
                                 @RequestParam String optionB,
                                 @RequestParam String optionC,
                                 @RequestParam String ans
    ) {
      return   questionService.createQue(title, optionA, optionB, optionC, ans);
    }


    @PostMapping("/admin/create_quiz")
    public String getQuestionsPage(Model m) {
        return questionService.getQuestionsPage(m);
    }


    @PostMapping("/submitquiz")
    public String submitQuiz(@ModelAttribute QuestionForm qForm, @RequestParam String title) {
       return  questionService.submitQuiz(qForm,title);
    }


    @GetMapping("/seequiz")
    public String seeQuiz(Model model){
        return questionService.seeQuiz(model);
    }

    @PostMapping("/getquiz")
    public String getQuiz(@RequestParam Integer idquiz,
                          @RequestParam String name,
                          @RequestParam String password,
                          Model m){
        if (userService.isUserLogined(name, password)) {
            return questionService.getQuiz(idquiz,name,m,result,submitted);
        }
        return "redirect:/register";
    }

}
