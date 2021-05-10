package com.fc.diray.main;

import com.fc.diray.bean.Data;
import com.fc.diray.utils.JdbcUtilsOnC3P0;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;


@WebServlet("/show")
public class show extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageNO = req.getParameter("page");
        //获取核心类对象
        QueryRunner queryRunner = new QueryRunner();

        //获取数据库连接
        Connection connection = JdbcUtilsOnC3P0.getConnection();

        //定义一个集合
        List<Data> query = null;

        //判断是否有页码
        if (pageNO!=null){
            int curPage = Integer.parseInt(pageNO);
            int pageSize=10;
            int startRow=(curPage-1)*pageSize;
            String sql = "select * from diray order by date desc limit ?,?";
            Object[] params = {startRow,pageSize};
            try {
                query = queryRunner.query(connection, sql, new BeanListHandler<>(Data.class), params);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            String sql = "select * from diray order by date desc";
            //System.out.println(sql);
            try {
                query = queryRunner.query(connection, sql, new BeanListHandler<>(Data.class));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
//        Data data = new Data();
//        System.out.println(data.getContent());
        //获取表中有多少数据
        String Page_sql = "select count(*) from diray";
        Map page = null;
        try {
            page = queryRunner.query(connection,Page_sql, new MapHandler());
        } catch (SQLException e) {
            e.printStackTrace();
        }

       /* //获取表中有多少数据
        String Last_Page_sql = "select * from fruit order by id DESC limit 1";
        Map Last_page = null;
        try {
            page = queryRunner.query(connection,Last_Page_sql, new MapHandler());
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
        //query.get(18).getId();
        //获取数据库中数据数目并且转化为页码
        String page_info1 = page.get("count(*)").toString();
        //获取页码数取整值
        int page_info_last = Integer.parseInt(page_info1)/10;
        //获取是否有余数
        int page_info3 = Integer.parseInt(page_info1)%10;
        //判断是否有余数
        if (page_info3>0){
            //有余数则页码多加一页
            page_info_last = page_info_last +1 ;
        }
        int pageNO_new = 1;
        if (pageNO!=null){
            //把当前页码转为int类型
            pageNO_new = Integer.parseInt(pageNO);
        }


        //调试是否查询成功
        //System.out.println(query);
        //resp.getWriter().append("<H1>"+query+"<H1>");
        if (query!=null && page!=null){
            req.setAttribute("query",query);//总数据
            req.setAttribute("page",pageNO_new);//当前页
            req.setAttribute("page_info",page_info_last);//最后一页
            req.getRequestDispatcher("show.jsp").forward(req,resp);
        }else {
            resp.getWriter().append("<h1>查询失败<h1><script>alert('查询为空')</script>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
