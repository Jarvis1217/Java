package com.myself.servlet01.Controller;

import com.alibaba.fastjson.JSON;
import com.myself.servlet01.Dao.Goods.GoodsMapper;
import com.myself.servlet01.Utils.MybatisUtil;
import com.myself.servlet01.pojo.Goods;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/goods")
public class goodsServlet extends HttpServlet {
    static SqlSession sqlSession;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");

        try{

            sqlSession = MybatisUtil.getSession();
            GoodsMapper mapper = sqlSession.getMapper(GoodsMapper.class);
            List<Goods> goods = mapper.getAllProducts();
            String s = JSON.toJSONString(goods);
            resp.getWriter().write(s);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
    }
}
