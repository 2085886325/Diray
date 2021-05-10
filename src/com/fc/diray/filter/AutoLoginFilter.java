package com.fc.diray.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 自动登录过滤器
 */
@WebFilter(urlPatterns={"/delete","/write"})
public class AutoLoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 强转，让请求和响应对象支持HTTP协议
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 获取session，
        HttpSession session = request.getSession(false);

        // 获取请求URI
        String uri = request.getRequestURI();

        // 判断
        if (uri.contains("/show") || uri.endsWith("/index.jsp")) {
//                || uri.endsWith("/register.html") || uri.endsWith("/register")
//                || uri.endsWith("/register_false.html")|| uri.endsWith("/login_false.html")) {
            // 放行
            filterChain.doFilter(request, response);

            // session及session中的属性不为空，说明是登录状态
        } else if (session != null && session.getAttribute("user") != null) {
            // 放行
            filterChain.doFilter(request, response);

            // 没有登录
        } else {
            // 跳转到登录页面
            response.sendRedirect("/index.jsp");
        }
    }

    @Override
    public void destroy() {

    }
}
