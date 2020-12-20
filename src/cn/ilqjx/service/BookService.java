package cn.ilqjx.service;

import cn.ilqjx.pojo.Book;
import cn.ilqjx.pojo.Page;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author upfly
 * @create 2020-12-09 11:10
 */
public interface BookService {

    /**
     * 保存图书信息
     *
     * @param book
     */
    void saveBook(Book book);

    /**
     * 根据 id 删除图书信息
     *
     * @param id
     */
    void deleteBookById(Integer id);

    /**
     * 修改图书信息
     *
     * @param book
     */
    void updateBook(Book book);

    /**
     * 根据 id 获取图书信息
     *
     * @param id
     * @return 图书信息
     */
    Book queryBookById(Integer id);

    /**
     * 获取全部图书信息
     *
     * @return 全部图书信息
     */
    List<Book> queryBooks();

    /**
     * 获取分页信息
     *
     * @param pageNo 页码
     * @param pageSize 每页显示数量
     * @return 分页信息
     */
    Page<Book> page(int pageNo, int pageSize);

    /**
     * 根据价格获取分页信息
     *
     * @param pageNo 页码
     * @param pageSize 每页显示数量
     * @param min 最小价格
     * @param max 最大价格
     * @return 分页信息
     */
    Page<Book> pageByPrice(int pageNo, int pageSize, BigDecimal min, BigDecimal max);
}
