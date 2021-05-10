package com.fc.diray.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 字符编码集过滤器
 */
@WebFilter(urlPatterns = "/*", initParams = {
        @WebInitParam(name = "characterEncoding", value = "UTF8"),
        @WebInitParam(name = "contextType", value = "text/html; charset=UTF-8")})
public class EncodingFilter implements Filter {
    // 声明编码集和文本内容
    private static String characterEncoding;
    private static String contextType;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        characterEncoding = filterConfig.getInitParameter("characterEncoding");
        contextType = filterConfig.getInitParameter("contextType");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 把请求对象和响应对象强转为支持HTTP协议的对象
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        request.setCharacterEncoding(characterEncoding);
        response.setContentType(contextType);

        // 放行
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
