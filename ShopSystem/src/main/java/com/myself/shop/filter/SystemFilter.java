package com.myself.shop.filter;

import com.myself.shop.pojo.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class SystemFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String requestURI = req.getRequestURI();
        User user = (User) req.getSession().getAttribute("user");

        if ("/shop/shop.jsp".equals(requestURI)) {
            if (user == null)
                resp.sendRedirect("/shop/login.jsp");
            else
                filterChain.doFilter(req, resp);
        } else {
            filterChain.doFilter(req, resp);
        }
    }
}
