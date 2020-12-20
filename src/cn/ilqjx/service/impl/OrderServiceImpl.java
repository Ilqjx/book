package cn.ilqjx.service.impl;

import cn.ilqjx.dao.BookDao;
import cn.ilqjx.dao.OrderDao;
import cn.ilqjx.dao.OrderItemDao;
import cn.ilqjx.dao.impl.BookDaoImpl;
import cn.ilqjx.dao.impl.OrderDaoImpl;
import cn.ilqjx.dao.impl.OrderItemDaoImpl;
import cn.ilqjx.pojo.*;
import cn.ilqjx.service.OrderService;

import java.util.Date;
import java.util.List;

/**
 * @author upfly
 * @create 2020-12-16 20:10
 */
public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private BookDao bookDao = new BookDaoImpl();

    @Override
    public String createOrder(Cart cart, Integer userId) {
        // 用时间戳加用户编号的方式生成唯一订单号
        String orderId = "" + System.currentTimeMillis() + userId;
        // 创建订单对象
        Order order = new Order(orderId, new Date(), cart.getTotalPrice(), Order.NOT_SEND, userId);
        // 保存订单
        orderDao.saveOrder(order);

        // 把商品项转换为订单项保存到数据库中
        for (CartItem cartItem : cart.getItems().values()) {
            OrderItem orderItem = new OrderItem(null, cartItem.getName(), cartItem.getCount(), cartItem.getPrice(),
                    cartItem.getTotalPrice(), orderId);
            orderItemDao.saveOrderItem(orderItem);

            // 更新商品库存和销量
            Book book = bookDao.queryBookById(cartItem.getId());
            book.setStock(book.getStock() - cartItem.getCount());
            book.setSales(book.getSales() + cartItem.getCount());
            bookDao.updateBook(book);
        }

        // 清空购物车
        cart.clear();

        return orderId;
    }

    @Override
    public void send(String orderId) {
        orderDao.changeOrderStatus(orderId, Order.SEND);
    }

    @Override
    public void receive(String orderId) {
        orderDao.changeOrderStatus(orderId, Order.RECEIVE);
    }

    @Override
    public List<Order> queryOrders() {
        return orderDao.queryOrders();
    }

    @Override
    public List<Order> queryOrdersByUserId(Integer userId) {
        return orderDao.queryOrdersByUserId(userId);
    }

    @Override
    public List<OrderItem> queryOrderItemsByOrderId(String orderId) {
        return orderItemDao.queryOrderItemsByOrderId(orderId);
    }
}
