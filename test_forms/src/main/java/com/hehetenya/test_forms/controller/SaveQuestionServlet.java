package com.hehetenya.test_forms.controller;

import com.hehetenya.test_forms.dto.TestDTO;
import com.hehetenya.test_forms.service.TestService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet("/saveQuestion")
public class SaveQuestionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String questionText = request.getParameter("questionText");
        int points = Integer.parseInt(request.getParameter("points"));
        List<String> correct = Arrays.asList(request.getParameterValues("correct"));
        List<String> incorrect = Arrays.asList(request.getParameterValues("incorrect"));
        TestDTO newTest = (TestDTO) request.getSession().getAttribute("newTest");
        TestService.addQuestionIntoTest(newTest, questionText, points, correct, incorrect);
        request.getSession().setAttribute("newTest", newTest);
        request.getRequestDispatcher("/WEB-INF/view/editTest.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
