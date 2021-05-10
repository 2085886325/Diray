package com.fc.diray.sign;

import com.fc.diray.bean.User;
import com.fc.diray.utils.JdbcUtilsOnC3P0;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        QueryRunner queryRunner = new QueryRunner();

        Connection connection = JdbcUtilsOnC3P0.getConnection();

        String sql = "select * from user where username = ? and password = ?";
//        System.out.println(sql);
        Object[] params = {username,password};
        User user = null;
        try {
             user = queryRunner.query(connection, sql, new BeanHandler<>(User.class), params);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 判断
        if (user != null) {
            HttpSession session = req.getSession(true);

            session.setAttribute("user", user.getUsername());

//            session.setAttribute("successful","successful");

            session.setMaxInactiveInterval(60 * 60);

            String id = session.getId();

            Cookie cookie = new Cookie("JSESSIONID", id);

            cookie.setMaxAge(60*60);

            resp.addCookie(cookie);

            // 登陆成功，转发到主页
            resp.sendRedirect("show?page=1");
//            resp.getWriter().write("<p>1111111</p>");
        }else {
            resp.sendRedirect("login_false.html");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
