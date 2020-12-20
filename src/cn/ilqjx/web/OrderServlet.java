package cn.ilqjx.web;

import cn.ilqjx.pojo.Cart;
import cn.ilqjx.pojo.Order;
import cn.ilqjx.pojo.OrderItem;
import cn.ilqjx.pojo.User;
import cn.ilqjx.service.OrderService;
import cn.ilqjx.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author upfly
 * @create 2020-12-16 20:43
 */
public class OrderServlet extends BaseServlet {
    private OrderService orderService = new OrderServiceImpl();

    /**
     * 结账，生成订单
     *
     * @param req
     * @param resp
     * @throws IOException
     */
    public void createOrder(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 从 session 中获取购物车对象和用户信息对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        User user = (User) req.getSession().getAttribute("user");

        // 用户登录并且购物车非空可以生成订单
        if (user != null && cart != null && cart.getItems().size() != 0) {
            // 生成订单
            String orderId = orderService.createOrder(cart, user.getId());
            // 把订单号放在 session 域中
            req.getSession().setAttribute("orderId", orderId);
            // 重定向解决页面刷新表单重复提交问题
            // 跳转到结算页面
            resp.sendRedirect(req.getContextPath() + "/pages/cart/checkout.jsp");
            return;
        }

        // 重定向到生成订单的原页面，解决浏览器回退重新点击【去结账】的问题
        resp.sendRedirect(req.getHeader("Referer"));
    }

    /**
     * 查看我的订单
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void showMyOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 从 session 中获取用户信息对象
        User user = (User) req.getSession().getAttribute("user");
        // 获取我的订单
        List<Order> myOrders = orderService.queryOrdersByUserId(user.getId());
        // 把订单信息放在 Request 域中
        req.setAttribute("myOrders", myOrders);
        // 请求转发到我的订单页面
        req.getRequestDispatcher("/pages/order/order.jsp").forward(req, resp);
    }

    /**
     * 确认签收
     *
     * @param req
     * @param resp
     * @throws IOException
     */
    public void receive(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 从 session 中获取用户信息对象
        User user = (User) req.getSession().getAttribute("user");
        // 获取订单号
        String orderId = req.getParameter("orderId");
        // 收货
        orderService.receive(orderId);
        // 重定向到我的订单页面
        resp.sendRedirect(req.getHeader("Referer"));
    }

    /**
     * 订单详情
     *
     * @param req
     * @param resp
     * @throws IOException
     * @throws ServletException
     */
    public void showOrderDetails(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        // 从 session 中获取用户信息对象
        User user = (User) req.getSession().getAttribute("user");
        // 获取订单号
        String orderId = req.getParameter("orderId");
        // 根据订单号查询订单项
        List<OrderItem> orderItems = orderService.queryOrderItemsByOrderId(orderId);
        // 把订单项放在 Request 域中
        req.setAttribute("orderItems", orderItems);
        // 请求转发到订单详情页面
        req.getRequestDispatcher("/pages/order/order_details.jsp").forward(req, resp);
    }

    /**
     * 订单管理，查看所有订单
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void showOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取所有订单
        List<Order> orders = orderService.queryOrders();
        // 把订单信息放在 Request 域中
        req.setAttribute("orders", orders);
        // 请求转发到订单管理页面
        req.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(req, resp);
    }

    /**
     * 发货
     *
     * @param req
     * @param resp
     * @throws IOException
     */
    public void send(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 获取订单号
        String orderId = req.getParameter("orderId");
        // 发货
        orderService.send(orderId);
        // 重定向回到订单管理页面
        resp.sendRedirect(req.getHeader("Referer"));
    }
}
