package com.hehetenya.test_forms.controller;

import com.hehetenya.test_forms.dto.TestDTO;
import com.hehetenya.test_forms.dto.UserDTO;
import com.hehetenya.test_forms.exeptions.DBException;
import com.hehetenya.test_forms.service.TestService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/addTest")
public class EditTestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDTO user = (UserDTO) request.getSession().getAttribute("user");
        TestDTO newTest = (TestDTO) request.getSession().getAttribute("newTest");
        if(newTest == null){
            newTest = new TestDTO(user.getLogin());
        }
        request.getSession().setAttribute("newTest", newTest);

        request.getRequestDispatcher("/WEB-INF/view/editTest.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TestDTO newTest = (TestDTO) request.getSession().getAttribute("newTest");
        try {
            TestService.createTest(newTest);
        }catch (DBException e){
            e.printStackTrace();
        }
        request.getSession().setAttribute("newTest", null);
        response.sendRedirect(request.getContextPath()+"/tests");
    }
}
