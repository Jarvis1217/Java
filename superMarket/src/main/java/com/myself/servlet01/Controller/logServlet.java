package com.myself.servlet01.Controller;

import com.myself.servlet01.Dao.User.UserMapper;
import com.myself.servlet01.Utils.MybatisUtil;
import com.myself.servlet01.pojo.User;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/check")
public class logServlet extends HttpServlet {
    private SqlSession sqlSession;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");

        String uname = req.getParameter("uname");
        String upass = req.getParameter("upass");

        try {

            sqlSession = MybatisUtil.getSession();
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            User user = mapper.getUserByName(uname);

            if (user == null)
                resp.getWriter().write("用户名有误");
            else if (!user.getUserPasswd().equals(upass))
                resp.getWriter().write("密码有误");
            else {
                req.getSession().setAttribute("User", user);

                if (user.getIsAdmin() == 1)
                    resp.getWriter().write("admin");
                else
                    resp.getWriter().write("index");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }
}
