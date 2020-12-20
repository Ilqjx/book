package cn.ilqjx.dao;

import cn.ilqjx.pojo.Order;

import java.util.List;

/**
 * @author upfly
 * @create 2020-12-16 19:30
 */
public interface OrderDao {

    /**
     * 保存订单
     *
     * @param order
     * @return 如果返回 -1 说明保存失败；返回其他说明保存成功。
     */
    int saveOrder(Order order);

    /**
     * 改变订单状态
     *
     * @param orderId 订单号
     * @param status 订单状态
     * @return 如果返回 -1 说明修改失败；返回其他说明修改成功。
     */
    int changeOrderStatus(String orderId, Integer status);

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
     * @return 查询到的订单信息
     */
    List<Order> queryOrdersByUserId(Integer userId);
}
