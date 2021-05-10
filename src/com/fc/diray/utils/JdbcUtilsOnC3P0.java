package com.fc.diray.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Jdbc工具类
 */
public class JdbcUtilsOnC3P0 {
    // 提取资源
    private static Connection connection = null;
    private static Statement statement = null;
    private static ResultSet resultSet = null;
    // 获取C3P0连接池对象
    private static ComboPooledDataSource pool = new ComboPooledDataSource();

    // 获取连接
    public static Connection getConnection() {
        try {
            connection = pool.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    // 关闭方法
    public static void close(Connection connection) {
        close(resultSet, statement, connection);
    }

    public static void close(Statement statement) {
        close(resultSet, statement, connection);
    }

    public static void close(Statement statement, Connection connection) {
        close(resultSet, statement, connection);
    }

    public static void close(ResultSet resultSet, Statement statement, Connection connection) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}







