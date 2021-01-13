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

@WebServlet("/add")
public class addServlet extends HttpServlet {
    static SqlSession session;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String price = req.getParameter("price");
        String product = req.getParameter("product");
        String stock = req.getParameter("stock");

        Map<String, String> productInfo = new HashMap<String,String>();

        productInfo.put("product",product);
        productInfo.put("price",price);
        productInfo.put("stock",stock);

        try {

            session = MybatisUtil.getSession();
            GoodsMapper mapper = session.getMapper(GoodsMapper.class);
            mapper.addProduct(productInfo);
            session.commit();

        }catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        }finally {
            session.close();
        }
    }
}
