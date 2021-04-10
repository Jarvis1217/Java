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

@WebServlet("/GoodsListWithType")
public class GoodsListWithType extends HttpServlet {
    private final GoodsDao goodsDao = new GoodsDaoImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter("type");
        List<Goods> allGoods = goodsDao.getGoodsWithType(type);

        req.getSession().setAttribute("allGoods",allGoods);
        req.getSession().setAttribute("type",type);
    }
}
