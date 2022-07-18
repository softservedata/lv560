package com.hehetenya.test_forms.controller;

import com.hehetenya.test_forms.exeptions.AppException;
import com.hehetenya.test_forms.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("user", null);
        request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try{
            UserService.tryToLogIn(request, response);
        }catch (Exception e){
            throw new AppException(e);
        }
    }
}
