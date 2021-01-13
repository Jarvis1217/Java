package com.myself.servlet01.filter;

import com.myself.servlet01.pojo.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class webfilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String requestURI = req.getRequestURI();
        User user = (User) req.getSession().getAttribute("User");

        switch (requestURI) {
            case "/sk/templates/admin.html":
                if (user == null)
                    resp.sendRedirect("http://localhost:8080/sk/templates/login.html");
                else if(user.getIsAdmin() == 1)
                    filterChain.doFilter(req, resp);
                else
                    resp.sendRedirect("http://localhost:8080/sk/templates/index.html");

                break;

            case "/sk/templates/index.html":
                if (user == null)
                    resp.sendRedirect("http://localhost:8080/sk/templates/login.html");
                else
                    filterChain.doFilter(req, resp);
                break;

            case "/sk/check":
            case "/sk/goods":
            case "/sk/add":
            case "/sk/delete":
            case "/sk/update":
            case "/sk/static/js/axios.min.js":
            case "/sk/static/js/jquery-3.5.1.min.js":
            case "/sk/static/js/vue.js":
            case "/sk/templates/login.html":
                filterChain.doFilter(req, resp);
                break;

            default:
                resp.sendRedirect("http://localhost:8080/sk/templates/login.html");
                break;
        }
    }
}
