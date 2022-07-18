package com.hehetenya.test_forms.filters;

import com.hehetenya.test_forms.dto.UserDTO;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * HttpFilter implementation to restrict access of not logged-in users to functional pages.
 */
@WebFilter({"/", "/home", "/tests", "/addQuestion", "/allResults", "/addTest", "/resultReview", "/test"})
public class LoggedOutFilter extends HttpFilter {
    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        UserDTO user = (UserDTO) request.getSession().getAttribute("user");
        if(user == null){
            response.sendRedirect(request.getContextPath()+ "/start");
        } else{
            chain.doFilter(request, response);
        }
    }
}
