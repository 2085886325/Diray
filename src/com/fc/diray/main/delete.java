package com.fc.diray.main;

import com.fc.diray.utils.JdbcUtilsOnC3P0;
import org.apache.commons.dbutils.QueryRunner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/delete")
public class delete extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取query.jsp拿到的id
        String id = req.getParameter("id");
        //获取核心类对象
        QueryRunner queryRunner = new QueryRunner();

        //获取数据库连接
        Connection connection = JdbcUtilsOnC3P0.getConnection();

        //准备sql语句
        String sql = "delete from diray where id = ?";

        //准备参数
        Object[] params ={id};

        try {
            queryRunner.update(connection, sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
