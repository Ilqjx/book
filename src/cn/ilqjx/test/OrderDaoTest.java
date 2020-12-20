package cn.ilqjx.test;

import cn.ilqjx.dao.OrderDao;
import cn.ilqjx.dao.impl.OrderDaoImpl;
import cn.ilqjx.pojo.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author upfly
 * @create 2020-12-16 19:42
 */
public class OrderDaoTest {
    private OrderDao orderDao = new OrderDaoImpl();
    @Test
    public void saveOrder() {
        Order order = new Order("1", new Date(), new BigDecimal(100), Order.NOT_SEND, 1);
        orderDao.saveOrder(order);
    }

    @Test
    public void changeOrderStatus() {
        orderDao.changeOrderStatus("1", Order.SEND);
    }

    @Test
    public void queryOrders() {
        orderDao.queryOrders().forEach(System.out::println);
    }

    @Test
    public void queryOrdersByUserId() {
        orderDao.queryOrdersByUserId(1).forEach(System.out::println);
    }
}