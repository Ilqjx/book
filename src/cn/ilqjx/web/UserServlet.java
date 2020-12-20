package cn.ilqjx.web;

import cn.ilqjx.pojo.User;
import cn.ilqjx.service.UserService;
import cn.ilqjx.service.impl.UserServiceImpl;
import cn.ilqjx.utils.WebUtils;
import com.google.code.kaptcha.Constants;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author upfly
 * @create 2020-12-07 18:57
 */
public class UserServlet extends BaseServlet {
    private UserService userService = new UserServiceImpl();

    /**
     * 登录功能
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1、将请求参数注入到 JavaBean 中
        User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());

        // 2、判断登录是否成功
        User loginUser = userService.login(user);
        if (loginUser == null) {
            // 登录失败
            // 把回显的错误信息和表单项信息放在 Request 域中
            req.setAttribute("errorMsg", "用户名或密码错误！");
            req.setAttribute("username", user.getUsername());

            // 请求转发跳转到登录页面
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        } else {
            // 免用户名登录
            // 创建 Cookie
            Cookie cookie = new Cookie("username", user.getUsername());
            // 设置最大生存时间为 7 天
            cookie.setMaxAge(7 * 24 * 60 * 60);
            // 设置有效路径
            cookie.setPath(req.getContextPath() + "/pages/user/");
            resp.addCookie(cookie);

            // 使用 session 保存用户登录后的信息
            loginUser.setPassword(null);
            req.getSession().setAttribute("user", loginUser);

            // 重定向解决刷新表单重复提交
            resp.sendRedirect(req.getContextPath() + "/pages/user/login_success.jsp");
        }
    }

    /**
     * 注销功能
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 从 session 中移除用户信息
        req.getSession().removeAttribute("user");
        // 重定向到首页
        resp.sendRedirect(req.getContextPath());
    }

    /**
     * 注册功能
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取 session 中的验证码
        String token = (String) req.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        // 删除 session 中的验证码
        req.getSession().removeAttribute(Constants.KAPTCHA_SESSION_KEY);

        // 1、把请求参数注入到 JavaBean 中和获取其他的请求参数
        User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());
        String code = req.getParameter("code");

        // 2、判断验证码是否正确
        if (token != null && token.equalsIgnoreCase(code)) {
            // 3、检查用户名是否可用
            if (userService.isExistForUsername(user.getUsername())) {
                // 把回显的错误信息和表单项信息放在 Request 域中
                req.setAttribute("errorMsg", "用户名已存在！");
                req.setAttribute("username", user.getUsername());
                req.setAttribute("email", user.getEmail());

                // 用户名不可用，跳转到注册页面 regist.jsp
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
            } else {
                // 4、注册用户，跳转到注册成功页面 regist_success.jsp
                userService.registUser(user);
                // 使用重定向避免表单重复提交
                resp.sendRedirect(req.getContextPath() + "/pages/user/regist_success.jsp");
            }
        } else {
            // 把回显的错误信息和表单项信息放在 Request 域中
            req.setAttribute("errorMsg", "验证码错误！");
            req.setAttribute("username", user.getUsername());
            req.setAttribute("email", user.getEmail());

            // 验证码错误，跳转到注册页面 regist.jsp
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
        }
    }

    /**
     * 检查用户名是否可用
     *
     * @param req
     * @param resp
     * @throws IOException
     */
    public void checkUsernameExists(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        boolean existForUsername = userService.isExistForUsername(username);

        // 返回提示信息的 map
        Map<String, Object> resultMap = new HashMap<>(1);

        if (existForUsername) {
            // 用户名存在，不可用
            resultMap.put("msg", "用户名已存在！");
        } else {
            // 用户名不存在，可用
            resultMap.put("msg", "用户名可用！");
        }

        Gson gson = new Gson();
        // 转换为 json 返回给客户端
        String msgJson = gson.toJson(resultMap);
        resp.getWriter().println(msgJson);
    }
}
