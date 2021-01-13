package com.myself.servlet01.Controller;

import com.myself.servlet01.Dao.Goods.GoodsMapper;
import com.myself.servlet01.Utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete")
public class deleteServlet extends HttpServlet {
    private SqlSession sqlSession;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String text = req.getParameter("text");

        try {

            sqlSession = MybatisUtil.getSession();
            GoodsMapper mapper = sqlSession.getMapper(GoodsMapper.class);
            mapper.deleteByName(text);
            sqlSession.commit();

        }catch(Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        }finally {
            sqlSession.close();
        }
    }
}
