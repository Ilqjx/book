package cn.ilqjx.service;

import cn.ilqjx.pojo.Cart;
import cn.ilqjx.pojo.Order;
import cn.ilqjx.pojo.OrderItem;

import java.util.List;

/**
 * @author upfly
 * @create 2020-12-16 20:05
 */
public interface OrderService {

    /**
     * 创建订单
     *
     * @param cart 购物车对象
     * @param userId 用户编号
     * @return 订单号
     */
    String createOrder(Cart cart, Integer userId);

    /**
     * 发货
     *
     * @param orderId 订单号
     */
    void send(String orderId);

    /**
     * 收货
     *
     * @param orderId
     */
    void receive(String orderId);

    /**
     * 查询全部订单信息
     *
     * @return 全部订单信息
     */
    List<Order> queryOrders();

    /**
     * 根据用户编号查询订单信息
     *
     * @param userId 用户编号
     * @return 此用户的订单信息
     */
    List<Order> queryOrdersByUserId(Integer userId);

    /**
     * 根据订单号查询订单项
     *
     * @param orderId 订单号
     * @return 此订单下的所有订单项
     */
    List<OrderItem> queryOrderItemsByOrderId(String orderId);
}
