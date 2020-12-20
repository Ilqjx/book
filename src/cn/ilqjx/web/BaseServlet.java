package cn.ilqjx.web;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author upfly
 * @create 2020-12-07 19:13
 */
public class BaseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        invokeMethod(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            // 设置请求体的字符集为 utf-8
            req.setCharacterEncoding("utf-8");
            invokeMethod(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void invokeMethod(HttpServletRequest req, HttpServletResponse resp) {
        // 解决中文响应乱码问题
        resp.setContentType("text/html; charset=UTF-8");

        // 获取请求参数 action
        String action = req.getParameter("action");

        try {
            // 通过反射根据 action 获取相应的方法
            Method method = this.getClass().getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            method.setAccessible(true);
            method.invoke(this, req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            // 如果出现异常，把异常抛给 TransactionFilter 进行事务回滚
            throw new RuntimeException();
        }
    }
}
