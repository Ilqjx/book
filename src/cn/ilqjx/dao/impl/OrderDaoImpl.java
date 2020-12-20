package cn.ilqjx.dao.impl;

import cn.ilqjx.dao.BaseDao;
import cn.ilqjx.dao.OrderDao;
import cn.ilqjx.pojo.Order;

import java.util.List;

/**
 * @author upfly
 * @create 2020-12-16 19:36
 */
public class OrderDaoImpl extends BaseDao<Order> implements OrderDao {

    @Override
    public int saveOrder(Order order) {
        String sql = "insert into t_order(`order_id`, `create_time`, `price`, `status`, `user_id`) values(?, ?, ?, ?, ?)";
        int count = update(sql, order.getOrderId(), order.getCreateTime(), order.getPrice(), order.getStatus(), order.getUserId());
        return count;
    }

    @Override
    public int changeOrderStatus(String orderId, Integer status) {
        String sql = "update t_order set `status` = ? where order_id = ?";
        int count = update(sql, status, orderId);
        return count;
    }

    @Override
    public List<Order> queryOrders() {
        String sql = "select `order_id` orderId, `create_time` createTime, `price`, `status`, `user_id` userId " +
                "from t_order order by create_time desc";
        List<Order> orders = queryForList(sql);
        return orders;
    }

    @Override
    public List<Order> queryOrdersByUserId(Integer userId) {
        String sql = "select `order_id` orderId, `create_time` createTime, `price`, `status`, `user_id` userId " +
                "from t_order where user_id = ? order by create_time desc";
        List<Order> orders = queryForList(sql, userId);
        return orders;
    }
}
