package com.hehetenya.test_forms.controller;

import com.hehetenya.test_forms.dto.ResultDTO;
import com.hehetenya.test_forms.service.ResultService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/allResults")
public class AllResultsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ResultDTO> results = ResultService.getAllResults();
        request.getSession().setAttribute("results", results);
        request.getRequestDispatcher("/WEB-INF/view/allResults.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
