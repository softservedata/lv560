package com.hehetenya.test_forms.controller;

import com.hehetenya.test_forms.dto.ResultDTO;
import com.hehetenya.test_forms.dto.UserDTO;
import com.hehetenya.test_forms.service.ResultService;
import com.hehetenya.test_forms.service.TestService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDTO user = (UserDTO) request.getSession().getAttribute("user");
        List<ResultDTO> results = ResultService.getAllResultsOfAUser(user);
        System.out.println("results size ==> " + results.size());
        request.getSession().setAttribute("results", results);
        request.getRequestDispatcher("/WEB-INF/view/home.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
