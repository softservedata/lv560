package com.hehetenya.test_forms.controller;

import com.hehetenya.test_forms.dto.AnswerDTO;
import com.hehetenya.test_forms.dto.QuestionDTO;
import com.hehetenya.test_forms.dto.TestDTO;
import com.hehetenya.test_forms.dto.UserDTO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet("/addQuestion")
public class AddQuestionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TestDTO newTest = (TestDTO) request.getSession().getAttribute("newTest");
        UserDTO user = (UserDTO) request.getSession().getAttribute("user");
        if(newTest == null){
            newTest = new TestDTO(user.getLogin());
        }
        newTest.setName(request.getParameter("testName"));
        System.out.println(newTest.getName());
        newTest.setDurationMinutes(Integer.parseInt(request.getParameter("duration")));
        System.out.println(newTest.getDurationMinutes());
        request.getSession().setAttribute("newTest", newTest);
        request.getRequestDispatcher("/WEB-INF/view/addQuestion.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("points ==>" + request.getParameter("points"));
        System.out.println("text ==>" + request.getParameter("questionText"));
        String questionText = request.getParameter("questionText");
        int points = Integer.parseInt(request.getParameter("points"));
        List<String> correct = Arrays.asList(request.getParameterValues("correct"));
        System.out.println("correct ==>" + correct);
        List<String> incorrect = Arrays.asList(request.getParameterValues("incorrect"));
        System.out.println("incorrect ==>" + incorrect);
        QuestionDTO newQuestion = new QuestionDTO(questionText, points, new ArrayList<>());
        for (String s: correct) {
            newQuestion.getAnswers().add(new AnswerDTO(s, true));
        }
        for (String s: incorrect) {
            newQuestion.getAnswers().add(new AnswerDTO(s, false));
        }
        TestDTO newTest = (TestDTO) request.getSession().getAttribute("newTest");
        newTest.getQuestions().add(newQuestion);
        request.getSession().setAttribute("newTest", newTest);
        request.getRequestDispatcher("/WEB-INF/view/editTest.jsp").forward(request, response);
    }
}
