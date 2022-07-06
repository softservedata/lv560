package com.hehetenya.test_forms.filters;

import com.hehetenya.test_forms.dto.UserDTO;
import com.hehetenya.test_forms.entity.Role;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter({"/addQuestion", "/allResults", "/addTest"})
public class UserFilter extends HttpFilter {

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        UserDTO user = (UserDTO) request.getSession().getAttribute("user");
        if(user.getRole() == Role.USER){
            response.sendRedirect(request.getContextPath()+ "/home");
        } else{
            chain.doFilter(request, response);
        }
    }
}
