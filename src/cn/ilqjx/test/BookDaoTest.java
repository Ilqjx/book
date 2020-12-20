package cn.ilqjx.test;

import cn.ilqjx.dao.BookDao;
import cn.ilqjx.dao.impl.BookDaoImpl;
import cn.ilqjx.pojo.Book;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author upfly
 * @create 2020-12-09 11:03
 */
public class BookDaoTest {
    private BookDao bookDao = new BookDaoImpl();

    @Test
    public void saveBook() {
        Book book = new Book(null, "今天我最帅", new BigDecimal(99), "伟神", 100, 0, null);
        bookDao.saveBook(book);
    }

    @Test
    public void deleteBookById() {
        bookDao.deleteBookById(482);
    }

    @Test
    public void updateBook() {
        Book book = new Book(483, "明天我还是最帅", new BigDecimal(99), "伟神", 100, 0, null);
        bookDao.updateBook(book);
    }

    @Test
    public void queryBookById() {
        System.out.println(bookDao.queryBookById(1));
    }

    @Test
    public void queryBooks() {
        bookDao.queryBooks().forEach(System.out::println);
    }

    @Test
    public void testQueryForPageTotalCount() {
        System.out.println(bookDao.queryForPageTotalCount());
    }

    @Test
    public void testQueryForPageItems() {
        List<Book> books = bookDao.queryForPageItems(20, 4);
        books.forEach(System.out::println);
    }

    @Test
    public void testQueryForPageTotalCountByPrice() {
        System.out.println(bookDao.queryForPageTotalCount(new BigDecimal(0), new BigDecimal(20)));
    }

    @Test
    public void testQueryForPageItemsByPrice() {
        List<Book> books = bookDao.queryForPageItems(4, 4, new BigDecimal(0), new BigDecimal(20));
        books.forEach(System.out::println);
    }
}