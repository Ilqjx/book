package cn.ilqjx.web;

import cn.ilqjx.pojo.Book;
import cn.ilqjx.pojo.Page;
import cn.ilqjx.service.BookService;
import cn.ilqjx.service.impl.BookServiceImpl;
import cn.ilqjx.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * @author upfly
 * @create 2020-12-12 10:40
 */
public class ClientBookServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();

    /**
     * 首页分页功能
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求参数 pageNo、pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);

        // 根据 pageNo、pageSize 获取 page 对象
        Page<Book> page = bookService.page(pageNo, pageSize);
        // 设置分页条的请求地址
        page.setUrl("client/book?action=page");
        req.setAttribute("page", page);

        // 请求转发到图书管理页面
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    }

    /**
     * 首页价格分页功能
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求参数
        String minStr = req.getParameter("min");
        String maxStr = req.getParameter("max");
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);

        BigDecimal min = WebUtils.parseBigDecimal(minStr, new BigDecimal(0));
        BigDecimal max = WebUtils.parseBigDecimal(maxStr, new BigDecimal(Integer.MAX_VALUE));

        // 获取分页对象
        Page<Book> page = bookService.pageByPrice(pageNo, pageSize, min, max);

        StringBuilder url = new StringBuilder();
        url.append("client/book?action=pageByPrice");
        if (minStr != null) {
            url.append("&min=" + minStr);
        }
        if (maxStr != null) {
            url.append("&max=" + maxStr);
        }

        // 设置分页请求地址
        page.setUrl(url.toString());

        // 把分页对象保存在 Request 域中
        req.setAttribute("page", page);

        // 请求转发到主页
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    }
}
