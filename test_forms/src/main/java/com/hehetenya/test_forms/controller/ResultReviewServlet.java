package com.hehetenya.test_forms.controller;

import com.hehetenya.test_forms.dto.ResultDTO;
import com.hehetenya.test_forms.service.ResultService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/resultReview")
public class ResultReviewServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int resultId = Integer.parseInt(request.getParameter("resultId"));
        ResultDTO resultDTO = ResultService.getResultById(resultId);
        request.getSession().setAttribute("result", resultDTO);
        request.getRequestDispatcher("/WEB-INF/view/resultReview.jsp").forward(request, response);
    }
}
