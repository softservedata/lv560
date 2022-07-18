package com.hehetenya.test_forms.controller;

import com.hehetenya.test_forms.dto.TestDTO;
import com.hehetenya.test_forms.service.TestService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/tests")
public class AllTestsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("newTest", null);
        request.getSession().setAttribute("tests", TestService.getAllTests());
        request.getRequestDispatcher("/WEB-INF/view/tests.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int testId = Integer.parseInt(request.getParameter("testId"));
        TestDTO test = TestService.getTest(testId);
        request.getSession().setAttribute("test", test);
        request.getRequestDispatcher("/WEB-INF/view/test-passing.jsp").forward(request, response);
    }
}
