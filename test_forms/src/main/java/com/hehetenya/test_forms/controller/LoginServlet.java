package com.hehetenya.test_forms.controller;

import com.hehetenya.test_forms.dto.UserDTO;
import com.hehetenya.test_forms.entity.Role;
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
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        if (login.isEmpty() || password.isEmpty()){
            response.sendRedirect(request.getContextPath() + "/login");
        }
        try{
            UserDTO user = new UserDTO(login, password);
            user = UserService.login(user);
            if(user == null){
                request.setAttribute("error", "true");
                response.sendRedirect(request.getContextPath() + "/login?err=");
            }
            request.getSession().setAttribute("user", user);
            response.sendRedirect(request.getContextPath() + "/home");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
