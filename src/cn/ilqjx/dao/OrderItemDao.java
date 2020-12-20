package cn.ilqjx.dao;

import cn.ilqjx.pojo.OrderItem;

import java.util.List;

/**
 * @author upfly
 * @create 2020-12-16 19:46
 */
public interface OrderItemDao {

    /**
     * 保存订单项
     *
     * @param orderItem
     * @return 如果返回 -1 说明保存失败；返回其他说明保存成功。
     */
    int saveOrderItem(OrderItem orderItem);

    /**
     * 根据订单号查询订单项
     *
     * @param orderId 订单号
     * @return 查询到的订单项
     */
    List<OrderItem> queryOrderItemsByOrderId(String orderId);
}
