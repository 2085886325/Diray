package com.fc.diray.sign;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        String id = null;
        if (session!=null){
            //获取session的id
            id = session.getId();

            // 说明当前还没有退出登录
            // 销毁session
            session.invalidate();
        }else {
            resp.sendRedirect("index.jsp");
        }

        Cookie cookie = new Cookie("JSESSIONID",id);

        cookie.setMaxAge(0);

        resp.addCookie(cookie);

        resp.sendRedirect("index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
