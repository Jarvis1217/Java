package com.myself.shop.servlet;

import com.myself.shop.dao.GoodsDao;
import com.myself.shop.dao.impl.GoodsDaoImpl;
import com.myself.shop.pojo.Goods;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/InsertServlet")
public class InsertServlet extends HttpServlet {
    private final GoodsDao goodsDao = new GoodsDaoImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String type = req.getParameter("type");
        Double price = Double.valueOf(req.getParameter("price"));
        Integer stock = Integer.valueOf(req.getParameter("stock"));
        String operator = req.getParameter("operator");

        Goods goods = new Goods(null, name, type, price, stock, operator, null);
        goodsDao.insertGoods(goods);
    }
}
