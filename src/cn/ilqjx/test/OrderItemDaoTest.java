package cn.ilqjx.test;

import cn.ilqjx.dao.OrderItemDao;
import cn.ilqjx.dao.impl.OrderItemDaoImpl;
import cn.ilqjx.pojo.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author upfly
 * @create 2020-12-16 20:00
 */
public class OrderItemDaoTest {
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();

    @Test
    public void saveOrderItem() {
        OrderItem orderItem1 = new OrderItem(null, "武炼巅峰", 1, new BigDecimal(100), new BigDecimal(100), "1");
        OrderItem orderItem2 = new OrderItem(null, "斗罗大陆", 2, new BigDecimal(20), new BigDecimal(40), "1");
        OrderItem orderItem3 = new OrderItem(null, "元尊", 1, new BigDecimal(5), new BigDecimal(5), "1");

        orderItemDao.saveOrderItem(orderItem1);
        orderItemDao.saveOrderItem(orderItem2);
        orderItemDao.saveOrderItem(orderItem3);
    }

    @Test
    public void queryOrderItemsByOrderId() {
        orderItemDao.queryOrderItemsByOrderId("1").forEach(System.out::println);
    }
}