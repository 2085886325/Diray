package com.fc.diray.main;

import com.fc.diray.bean.Data;
import com.fc.diray.utils.JdbcUtilsOnC3P0;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

@WebServlet("/write")
public class write extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        // 执行SQL语句
        if (session==null){
            resp.getWriter().append("<h1>提交失败！<h1><script>alert('请登录')</script>");
        } else {


            Map<String, String[]> parameterMap = req.getParameterMap();

            Data data = new Data();

            try {
                BeanUtils.populate(data, parameterMap);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

            // 获取DbUtils核心类对象
            QueryRunner queryRunner = new QueryRunner();

            // 获取通过c3p0获取数据库连接
            Connection connection = JdbcUtilsOnC3P0.getConnection();

            // 准备SQL语句
            String sql = "insert into diray(title, content, date) values(?, ?, ?)";

            // 准备参数
            Object[] params = {data.getTitle(), data.getContent(), data.getDate2()};


            try {
                int update = queryRunner.update(connection, sql, params);
                if (update != 0) {
                    resp.sendRedirect("show");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
