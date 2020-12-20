package cn.ilqjx.dao.impl;

import cn.ilqjx.dao.BaseDao;
import cn.ilqjx.dao.BookDao;
import cn.ilqjx.pojo.Book;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author upfly
 * @create 2020-12-09 10:53
 */
public class BookDaoImpl extends BaseDao<Book> implements BookDao {

    @Override
    public int saveBook(Book book) {
        String sql = "insert into t_book(`name`, `author`, `price`, `sales`, `stock`, `img_path`) values(?, ?, ?, ?, ?, ?)";
        int count = update(sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImgPath());
        return count;
    }

    @Override
    public int deleteBookById(Integer id) {
        String sql = "delete from t_book where id = ?";
        int count = update(sql, id);
        return count;
    }

    @Override
    public int updateBook(Book book) {
        String sql = "update t_book set `name` = ?, `author` = ?, `price` = ?, `sales` = ?, `stock` = ?, `img_path` = ? where id = ?";
        int count = update(sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImgPath(), book.getId());
        return count;
    }

    @Override
    public Book queryBookById(Integer id) {
        String sql = "select `id`, `name`, `author`, `price`, `sales`, `stock`, `img_path` imgPath from t_book where id = ?";
        Book book = queryForOne(sql, id);
        return book;
    }

    @Override
    public List<Book> queryBooks() {
        String sql = "select `id`, `name`, `author`, `price`, `sales`, `stock`, `img_path` imgPath " +
                "from t_book";
        List<Book> books = queryForList(sql);
        return books;
    }

    @Override
    public Integer queryForPageTotalCount() {
        String sql = "select count(*) from t_book";
        // 注意：返回的结果为 Long 类型的
        Long pageTotalCount = queryForSingleValue(sql);
        return pageTotalCount.intValue();
    }

    @Override
    public List<Book> queryForPageItems(int begin, int pageSize) {
        String sql = "select `id`, `name`, `author`, `price`, `sales`, `stock`, `img_path` imgPath " +
                "from t_book limit ?, ?";
        List<Book> books = queryForList(sql, begin, pageSize);
        return books;
    }

    @Override
    public Integer queryForPageTotalCount(BigDecimal min, BigDecimal max) {
        String sql = "select count(*) from t_book where price between ? and ?";
        Long pageTotalCount = queryForSingleValue(sql, min, max);
        return pageTotalCount.intValue();
    }

    @Override
    public List<Book> queryForPageItems(int begin, int pageSize, BigDecimal min, BigDecimal max) {
        String sql = "select `id`, `name`, `author`, `price`, `sales`, `stock`, `img_path` imgPath " +
                "from t_book where price between ? and ? order by price asc limit ?, ?";
        List<Book> books = queryForList(sql, min, max, begin, pageSize);
        return books;
    }
}
