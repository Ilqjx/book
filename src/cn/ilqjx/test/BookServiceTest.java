package cn.ilqjx.test;

import cn.ilqjx.pojo.Book;
import cn.ilqjx.service.BookService;
import cn.ilqjx.service.impl.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author upfly
 * @create 2020-12-09 11:15
 */
public class BookServiceTest {
    private BookService bookService = new BookServiceImpl();

    @Test
    public void saveBook() {
        Book book = new Book(null, "今天我最帅", new BigDecimal(99), "伟神", 100, 0, null);
        bookService.saveBook(book);
    }

    @Test
    public void deleteBookById() {
        bookService.deleteBookById(483);
    }

    @Test
    public void updateBook() {
        Book book = new Book(484, "明天我还是最帅", new BigDecimal(99), "伟神", 100, 0, null);
        bookService.updateBook(book);
    }

    @Test
    public void queryBookById() {
        System.out.println(bookService.queryBookById(1));
    }

    @Test
    public void queryBooks() {
        bookService.queryBooks().forEach(System.out::println);
    }

    @Test
    public void testPage() {
        System.out.println(bookService.page(6, 4));
    }

    @Test
    public void testPageByPrice() {
        System.out.println(bookService.pageByPrice(2, 4, new BigDecimal(0), new BigDecimal(20)));
    }
}