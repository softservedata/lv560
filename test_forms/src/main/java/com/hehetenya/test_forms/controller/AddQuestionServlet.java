package com.hehetenya.test_forms.controller;


import com.hehetenya.test_forms.dto.TestDTO;
import com.hehetenya.test_forms.dto.UserDTO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/addQuestion")
public class AddQuestionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TestDTO newTest = (TestDTO) request.getSession().getAttribute("newTest");
        if(newTest == null){
            newTest = new TestDTO();
        }
        newTest.setName(request.getParameter("testName"));
        request.getSession().setAttribute("newTest", newTest);
        request.getRequestDispatcher("/WEB-INF/view/addQuestion.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
