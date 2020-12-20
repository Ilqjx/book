package cn.ilqjx.filter;

import cn.ilqjx.pojo.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author upfly
 * @create 2020-12-18 14:23
 */
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // ServletRequest 没有 getSession() 方法，需要转换为 HttpServletRequest
        HttpServletRequest req = (HttpServletRequest) request;
        // ServletResponse 没有 sendRedirect() 方法，需要转换为 HttpServletResponse
        HttpServletResponse resp = (HttpServletResponse) response;

        // 从 session 中获取用户信息
        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
            // 用户未登录
            // 重定向到登录页面
            resp.sendRedirect(req.getContextPath() + "/pages/user/login.jsp");
        } else {
            // 用户已登录
            // 放行，访问下一个过滤器或目标资源
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {}
}
