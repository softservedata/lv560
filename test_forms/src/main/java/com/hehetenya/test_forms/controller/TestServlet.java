package com.hehetenya.test_forms.controller;

import com.hehetenya.test_forms.dto.ResultDTO;
import com.hehetenya.test_forms.dto.TestDTO;
import com.hehetenya.test_forms.dto.UserDTO;
import com.hehetenya.test_forms.service.AnswerService;
import com.hehetenya.test_forms.service.ResultService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet("/test")
public class TestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> usersAnswers = Arrays.asList(request.getParameterValues("answerId"));
        UserDTO user = (UserDTO) request.getSession().getAttribute("user");
        TestDTO test = (TestDTO) request.getSession().getAttribute("test");
        ResultService.sendNewResult(new ResultDTO(user, test, AnswerService.getUserAnswers(usersAnswers)));
        response.sendRedirect(request.getContextPath() + "/home");
    }
}
