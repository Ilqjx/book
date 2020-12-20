package cn.ilqjx.test;

import cn.ilqjx.pojo.Cart;
import cn.ilqjx.pojo.CartItem;
import cn.ilqjx.service.OrderService;
import cn.ilqjx.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author upfly
 * @create 2020-12-16 20:27
 */
public class OrderServiceTest {
    private OrderService orderService = new OrderServiceImpl();

    @Test
    public void saveOrder() {
        Cart cart = new Cart();
        CartItem cartItem1 = new CartItem(1, "武炼巅峰", 1, new BigDecimal(100), new BigDecimal(100));
        CartItem cartItem2 = new CartItem(1, "武炼巅峰", 1, new BigDecimal(100), new BigDecimal(100));
        CartItem cartItem3 = new CartItem(2, "元尊", 2, new BigDecimal(10), new BigDecimal(20));
        cart.addItem(cartItem1);
        cart.addItem(cartItem2);
        cart.addItem(cartItem3);

        orderService.createOrder(cart, 1);
    }

    @Test
    public void send() {
        orderService.send("16081224005431");
    }

    @Test
    public void receive() {
        orderService.receive("16081224005431");
    }

    @Test
    public void queryOrders() {
        orderService.queryOrders().forEach(System.out::println);
    }

    @Test
    public void queryOrdersByUserId() {
        orderService.queryOrdersByUserId(1).forEach(System.out::println);
    }

    @Test
    public void queryOrderItemsByOrderId() {
        orderService.queryOrderItemsByOrderId("16081224005431").forEach(System.out::println);
    }
}