package cn.ilqjx.filter;

import cn.ilqjx.utils.JdbcUtils;

import javax.servlet.*;

/**
 * @author upfly
 * @create 2020-12-18 21:06
 */
public class TransactionFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        try {
            chain.doFilter(request, response);
            JdbcUtils.commitAndClose();
        } catch (Exception e) {
            JdbcUtils.rollbackAndClose();
            e.printStackTrace();
            // 抛异常，为了显示错误信息页面
            throw new RuntimeException();
        }
    }

    @Override
    public void destroy() {}
}
