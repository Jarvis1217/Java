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
import java.util.List;

@WebServlet("/GoodsList")
public class GoodsList extends HttpServlet {
    private final GoodsDao goodsDao = new GoodsDaoImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Goods> allGoods = goodsDao.getAllGoods();
        req.getSession().setAttribute("allGoods",allGoods);

        req.getSession().setAttribute("type","全部分类");
        resp.sendRedirect("/shop/shop.jsp");
    }
}
