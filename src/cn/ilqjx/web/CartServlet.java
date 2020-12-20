package cn.ilqjx.web;

import cn.ilqjx.pojo.Book;
import cn.ilqjx.pojo.Cart;
import cn.ilqjx.pojo.CartItem;
import cn.ilqjx.service.BookService;
import cn.ilqjx.service.impl.BookServiceImpl;
import cn.ilqjx.utils.WebUtils;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author upfly
 * @create 2020-12-16 14:34
 */
public class CartServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();

    /**
     * 加入购物车
     *
     * @param req
     * @param resp
     * @throws IOException
     */
    public void addItem(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null) {
            // 第一次添加需要创建购物车对象并放在 session 域中
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }

        // 根据图书编号获取图书信息
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        Book book = bookService.queryBookById(id);

        if (book != null) {
            // 创建商品项对象
            CartItem cartItem = new CartItem(book.getId(), book.getName(), CartItem.DEFAULT_COUNT, book.getPrice(), book.getPrice());
            // 添加商品项
            cart.addItem(cartItem);

            // 向 session 域中添加最后一次加入购物车的商品项的商品名称
            req.getSession().setAttribute("lastName", cartItem.getName());

            // 返回信息的 map
            Map<String, Object> resultMap = new HashMap<>(2);
            resultMap.put("totalCount", cart.getTotalCount());
            resultMap.put("lastName", cartItem.getName());

            // 把 map 转换为 json 字符串
            Gson gson = new Gson();
            String json = gson.toJson(resultMap);

            // 将 json 返回给客户端
            resp.getWriter().println(json);
        }
    }

    /**
     * 删除商品项
     *
     * @param req
     * @param resp
     * @throws IOException
     */
    public void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 从 session 域获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {
            // 获取商品编号
            int id = WebUtils.parseInt(req.getParameter("id"), 0);
            // 删除商品项
            cart.deleteItemById(id);
        }

        // 重定向回到删除商品项的页面
        resp.sendRedirect(req.getHeader("Referer"));
    }

    /**
     * 清空购物车
     *
     * @param req
     * @param resp
     * @throws IOException
     */
    public void clear(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 从 session 域中获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {
            // 清空购物车
            cart.clear();
        }

        // 重定向回到清空购物车的页面
        resp.sendRedirect(req.getHeader("Referer"));
    }

    /**
     * 修改商品项数量
     *
     * @param req
     * @param resp
     * @throws IOException
     */
    public void updateItemCount(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 从 session 域获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {
            // 获取商品编号
            int id = WebUtils.parseInt(req.getParameter("id"), 0);
            // 获取要修改为的数量
            int count = WebUtils.parseInt(req.getParameter("count"), CartItem.DEFAULT_COUNT);
            // 修改商品项数量
            cart.updateItemCount(id, count);
        }

        // 重定向回到更新商品项数量的页面
        resp.sendRedirect(req.getHeader("Referer"));
    }
}
