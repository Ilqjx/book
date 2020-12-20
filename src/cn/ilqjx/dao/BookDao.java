package cn.ilqjx.dao;

import cn.ilqjx.pojo.Book;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author upfly
 * @create 2020-12-09 10:45
 */
public interface BookDao {

    /**
     * 保存图书信息
     *
     * @param book
     * @return 如果返回 -1 说明保存失败；否则返回影响数据库的行数。
     */
    int saveBook(Book book);

    /**
     * 根据 id 删除图书信息
     *
     * @param id
     * @return 如果返回 -1 说明删除失败；否则返回影响数据库的行数。
     */
    int deleteBookById(Integer id);

    /**
     * 修改图书信息
     *
     * @param book
     * @return 如果返回 -1 说明修改失败；否则返回影响数据库的行数。
     */
    int updateBook(Book book);

    /**
     * 根据 id 查询图书信息
     *
     * @param id
     * @return 返回查询到的图书信息
     */
    Book queryBookById(Integer id);

    /**
     * 查询全部图书信息
     *
     * @return 返回查询到的全部图书信息
     */
    List<Book> queryBooks();

    /**
     * 查询总记录数
     *
     * @return 总记录数
     */
    Integer queryForPageTotalCount();

    /**
     * 获取当前页数据
     *
     * @param begin 当前页开始索引
     * @param pageSize 查询数量
     * @return 查询到的图书信息
     */
    List<Book> queryForPageItems(int begin, int pageSize);

    /**
     * 根据价格获取总记录数
     *
     * @param min 最小价格
     * @param max 最大价格
     * @return 根据价格获取到的总记录数
     */
    Integer queryForPageTotalCount(BigDecimal min, BigDecimal max);

    /**
     * 根据价格获取当前页数据
     *
     * @param begin 当前页的开始索引
     * @param pageSize 查询数量
     * @param min 最小价格
     * @param max 最大价格
     * @return 查询到的图书信息
     */
    List<Book> queryForPageItems(int begin, int pageSize, BigDecimal min, BigDecimal max);
}
