package cn.ilqjx.pojo;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author upfly
 * @create 2020-12-16 13:52
 */
public class Cart {
    /**
     * 购物车商品
     */
    private Map<Integer, CartItem> items = new LinkedHashMap<>();

    /**
     * 增加商品项
     *
     * @param cartItem 购物车商品项
     */
    public void addItem(CartItem cartItem) {
        // 如果已经添加，则数量累加，更新商品总价；如果没有添加，则添加。
        CartItem item = items.get(cartItem.getId());
        if (item != null) {
            // 已添加
            // 数量累加
            item.setCount(item.getCount() + cartItem.getCount());
            // 更新商品总价
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        } else {
            // 未添加
            items.put(cartItem.getId(), cartItem);
        }
    }

    /**
     * 通过商品编号删除商品项
     *
     * @param id 商品编号
     */
    public void deleteItemById(Integer id) {
        items.remove(id);
    }

    /**
     * 修改商品项数量
     *
     * @param id 商品编号
     * @param count 要修改为的商品数量
     */
    public void updateItemCount(Integer id, Integer count) {
        CartItem cartItem = items.get(id);
        if (cartItem != null) {
            // 更新商品数量
            cartItem.setCount(count);
            // 更新商品总价
            cartItem.setTotalPrice(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount())));
        }
    }

    /**
     * 清空购物车
     */
    public void clear() {
        items.clear();
    }

    /**
     * 获取总商品数量
     *
     * @return 总商品数量
     */
    public Integer getTotalCount() {
        Integer totalCount = 0;
        for (CartItem cartItem : items.values()) {
            totalCount += cartItem.getCount();
        }
        return totalCount;
    }

    /**
     * 获取总商品价格
     *
     * @return 总商品价格
     */
    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);
        for (CartItem cartItem : items.values()) {
            totalPrice = totalPrice.add(cartItem.getTotalPrice());
        }
        return totalPrice;
    }

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }
}
