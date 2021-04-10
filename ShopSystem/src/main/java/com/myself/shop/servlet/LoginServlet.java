package com.myself.shop.servlet;

import com.myself.shop.dao.UserDao;
import com.myself.shop.dao.impl.UserDaoImpl;
import com.myself.shop.pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final UserDao userDao = new UserDaoImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uname = req.getParameter("username");
        String passwd = req.getParameter("password");

        User user = userDao.getUserByName(uname);

        if(user.getPasswd() == null) {
            req.getSession().setAttribute("errCode","账号未注册");
            resp.sendRedirect("/shop/login.jsp");
        }else if(!user.getPasswd().equals(passwd)) {
            req.getSession().setAttribute("errCode","登录密码有误");
            resp.sendRedirect("/shop/login.jsp");
        }else {
            req.getRequestDispatcher("GoodsList").forward(req,resp);
            req.getSession().setAttribute("user",user);
        }
    }
}
