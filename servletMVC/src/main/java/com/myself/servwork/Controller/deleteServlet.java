package com.myself.servwork.Controller;

import com.myself.servwork.Dao.Impl.shoppingDaoImpl;
import com.myself.servwork.Dao.shoppingDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete")
public class deleteServlet extends HttpServlet {
    private final shoppingDao shopDao = new shoppingDaoImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String text = req.getParameter("text").trim();
        shopDao.deleteByText(text);
    }
}
