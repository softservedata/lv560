package com.hehetenya.test_forms.controller;

import com.hehetenya.test_forms.dto.UserDTO;
import com.hehetenya.test_forms.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/view/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        if (login.isEmpty() || password.isEmpty()){
            response.sendRedirect(request.getContextPath() + "/login");
        }
        try{
            UserDTO user = new UserDTO(login, password);
            if(UserService.login(user) != null){
                request.setAttribute("error", "true");
                response.sendRedirect(request.getContextPath() + "/register?err=");
            } else{
                user = UserService.register(user);
                request.getSession().setAttribute("user", user);
                response.sendRedirect(request.getContextPath() + "/home");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
