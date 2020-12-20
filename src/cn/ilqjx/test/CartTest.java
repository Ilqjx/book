package cn.ilqjx.test;

import cn.ilqjx.pojo.Cart;
import cn.ilqjx.pojo.CartItem;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author upfly
 * @create 2020-12-16 14:43
 */
public class CartTest {
    private Cart cart = new Cart();

    @Test
    public void addItem() {
        CartItem cartItem1 = new CartItem(1, "武炼巅峰", 1, new BigDecimal(100), new BigDecimal(100));
        CartItem cartItem2 = new CartItem(1, "武炼巅峰", 1, new BigDecimal(100), new BigDecimal(100));
        CartItem cartItem3 = new CartItem(2, "元尊", 2, new BigDecimal(10), new BigDecimal(20));
        cart.addItem(cartItem1);
        cart.addItem(cartItem2);
        cart.addItem(cartItem3);
        System.out.println(cart);
    }

    @Test
    public void deleteItemById() {
        addItem();
        cart.deleteItemById(1);
        System.out.println(cart);
    }

    @Test
    public void updateItemCount() {
        addItem();
        cart.updateItemCount(1, 10);
        System.out.println(cart);
    }

    @Test
    public void clear() {
        addItem();
        cart.clear();
        System.out.println(cart);
    }

    @Test
    public void getItems() {
        addItem();
        Map<Integer, CartItem> items = cart.getItems();
        for (Map.Entry<Integer, CartItem> entry : items.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}