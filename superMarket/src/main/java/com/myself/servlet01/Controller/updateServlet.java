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
import java.util.HashMap;
import java.util.Map;

@WebServlet("/update")
public class updateServlet extends HttpServlet {
    private SqlSession sqlSession;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String proName = req.getParameter("proName");
        String proPrice = req.getParameter("proPrice");
        String proStock = req.getParameter("proStock");

        Map<String,String> proInfo = new HashMap<>();
        proInfo.put("proName",proName);
        proInfo.put("proPrice",proPrice);
        proInfo.put("proStock",proStock);

        try {

            sqlSession = MybatisUtil.getSession();
            GoodsMapper mapper = sqlSession.getMapper(GoodsMapper.class);
            mapper.updateByName(proInfo);
            sqlSession.commit();

        }catch(Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        }finally {
            sqlSession.close();
        }
    }
}
