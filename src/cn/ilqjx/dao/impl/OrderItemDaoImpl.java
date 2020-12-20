package cn.ilqjx.dao.impl;

import cn.ilqjx.dao.BaseDao;
import cn.ilqjx.dao.OrderItemDao;
import cn.ilqjx.pojo.OrderItem;

import java.util.List;

/**
 * @author upfly
 * @create 2020-12-16 19:56
 */
public class OrderItemDaoImpl extends BaseDao<OrderItem> implements OrderItemDao {

    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql = "insert into t_order_item(`name`, `count`, `price`, `total_price`, `order_id`) values(?, ?, ?, ?, ?)";
        int count = update(sql, orderItem.getName(), orderItem.getCount(), orderItem.getPrice(), orderItem.getTotalPrice(), orderItem.getOrderId());
        return count;
    }

    @Override
    public List<OrderItem> queryOrderItemsByOrderId(String orderId) {
        String sql = "select `id`, `name`, `count`, `price`, `total_price` totalPrice, `order_id` orderId " +
                "from t_order_item where order_id = ?";
        List<OrderItem> orderItems = queryForList(sql, orderId);
        return orderItems;
    }
}
