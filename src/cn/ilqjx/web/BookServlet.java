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
import java.util.List;

/**
 * @author upfly
 * @create 2020-12-09 11:18
 */
public class BookServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();

    /**
     * 保存图书信息
     *
     * @param req
     * @param resp
     * @throws IOException
     */
    public void save(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 默认值 0，因为要加 1
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 0);
        // 保证跳转的是最后一页
        // 如果增加一条数据后，恰好多出来一页，最后一页就要 + 1
        // 如果没有多出来一页，setPageNo() 时会进行数据边界的有效检查，还是最后一页
        pageNo++;

        // 把请求参数注入到 Book 对象中
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());
        // 保存
        bookService.saveBook(book);
        // 重定向到图书管理页面
        resp.sendRedirect(req.getContextPath() + "/manager/book?action=page&pageNo=" + pageNo);
    }

    /**
     * 删除图书信息
     *
     * @param req
     * @param resp
     * @throws IOException
     */
    public void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 获取请求参数 id
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        // 删除
        bookService.deleteBookById(id);
        // 重定向到图书管理页面
        resp.sendRedirect(req.getContextPath() + "/manager/book?action=page&pageNo=" + req.getParameter("pageNo"));
    }

    /**
     * 更新图书信息
     *
     * @param req
     * @param resp
     * @throws IOException
     */
    public void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 把请求参数注入到 Book 对象中
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());
        // 修改图书信息
        bookService.updateBook(book);
        // 重定向到图书管理页面
        resp.sendRedirect(req.getContextPath() + "/manager/book?action=page&pageNo=" + req.getParameter("pageNo"));
    }

    /**
     * 获取图书信息
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void get(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求参数 id
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        // 获取图书信息
        Book book = bookService.queryBookById(id);
        // 把图书信息放在 Request 域中
        req.setAttribute("book", book);
        // 请求转发到图书编辑页面
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req, resp);
    }

    /**
     * 获取全部图书信息
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取全部图书信息并放在 Request 域中
        List<Book> books = bookService.queryBooks();
        req.setAttribute("books", books);

        // 请求转发，跳转到图书管理页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }

    /**
     * 图书管理页面分页功能
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
        page.setUrl("manager/book?action=page");
        req.setAttribute("page", page);

        // 请求转发到图书管理页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }
}
