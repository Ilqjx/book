package cn.ilqjx.service.impl;

import cn.ilqjx.dao.BookDao;
import cn.ilqjx.dao.impl.BookDaoImpl;
import cn.ilqjx.pojo.Book;
import cn.ilqjx.pojo.Page;
import cn.ilqjx.service.BookService;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author upfly
 * @create 2020-12-09 11:13
 */
public class BookServiceImpl implements BookService {
    private BookDao bookDao = new BookDaoImpl();

    @Override
    public void saveBook(Book book) {
        bookDao.saveBook(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        bookDao.deleteBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Page<Book> page = new Page<>();
        // 设置每页显示数量
        page.setPageSize(pageSize);

        // 获取总记录数
        Integer pageTotalCount = bookDao.queryForPageTotalCount();
        // 设置总记录数
        page.setPageTotalCount(pageTotalCount);

        // 获取总页码
        Integer pageTotal = pageTotalCount % pageSize == 0 ?
                pageTotalCount / pageSize : pageTotalCount / pageSize + 1;
        // 设置总页码
        page.setPageTotal(pageTotal);

        // 设置页码
        // 设置页码之前需要先设置总页码，因为页码的数据边界处理需要使用总页码
        page.setPageNo(pageNo);

        // 获取当前页数据的开始索引
        int begin = (page.getPageNo() - 1) * pageSize;
        // 获取当前页数据
        List<Book> items = bookDao.queryForPageItems(begin, pageSize);
        // 设置当前页数据
        page.setItems(items);

        return page;
    }

    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, BigDecimal min, BigDecimal max) {
        Page<Book> page = new Page<>();
        // 设置每页显示数量
        page.setPageSize(pageSize);

        // 获取总记录数
        Integer pageTotalCount = bookDao.queryForPageTotalCount(min, max);
        // 设置总记录数
        page.setPageTotalCount(pageTotalCount);

        // 获取总页码
        Integer pageTotal = pageTotalCount % pageSize == 0 ?
                pageTotalCount / pageSize : pageTotalCount / pageSize + 1;
        // 设置总页码
        page.setPageTotal(pageTotal);

        // 设置页码
        // 设置页码之前需要先设置总页码，因为页码的数据边界处理需要使用总页码
        page.setPageNo(pageNo);

        // 获取当前页数据的开始索引
        // 这里要通过 page.getPageNo() 来获取页码，因为这个页码是经过数据边界有效检查的
        int begin = (page.getPageNo() - 1) * pageSize;
        // 获取当前页数据
        List<Book> items = bookDao.queryForPageItems(begin, pageSize, min, max);
        // 设置当前页数据
        page.setItems(items);

        return page;
    }
}
