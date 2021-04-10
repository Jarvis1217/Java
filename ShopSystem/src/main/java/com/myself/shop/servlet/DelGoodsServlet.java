package com.myself.shop.servlet;

import com.myself.shop.dao.GoodsDao;
import com.myself.shop.dao.impl.GoodsDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DelGoodsServlet")
public class DelGoodsServlet extends HttpServlet {
    private final GoodsDao goodsDao = new GoodsDaoImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        goodsDao.delGoodsById(Integer.parseInt(req.getParameter("id")));
    }
}
