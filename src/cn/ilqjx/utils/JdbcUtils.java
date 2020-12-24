package cn.ilqjx.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.commons.dbutils.DbUtils;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author upfly
 * @create 2020-11-29 16:44
 */
public class JdbcUtils {
    private static DataSource dataSource;
    private static ThreadLocal<Connection> tl = new ThreadLocal<>();

    static {
        try {
            Properties pros = new Properties();
            InputStream is = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            pros.load(is);
            dataSource = DruidDataSourceFactory.createDataSource(pros);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 从数据库连接池中获取一个数据库连接
     *
     * @return 如果返回 null 说明获取连接失败，否则返回数据库连接池中的一个数据库连接。
     */
    public static Connection getConnection() {
        // 获取数据库连接
        Connection conn = tl.get();
        if (conn == null) {
            try {
                // 从数据库连接池中获取一个数据库连接
                conn = dataSource.getConnection();
                // 关闭自动提交
                conn.setAutoCommit(false);
                // 把 conn 和当前线程关联起来
                tl.set(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

    /**
     * 提交事务，并且关闭释放连接
     */
    public static void commitAndClose() {
        Connection conn = tl.get();
        // 如果连接不为空，说明之前使用过连接
        if (conn != null) {
            try {
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                // remove(): 将 ThreadLocal 从当前线程的 ThreadLocalMap 中移除
                // Tomcat 底层使用的是线程池，如果不调用 remove() 方法，之后在使用这个线程时，
                // 获取的连接就不是空的，但是这个连接在第一次使用之后就已经关闭了。
                tl.remove();
                close(conn);
            }
        }
    }

    /**
     * 回滚事务，并且关闭释放连接
     */
    public static void rollbackAndClose() {
        Connection conn = tl.get();
        // 如果连接不为空，说明之前使用过连接
        if (conn != null) {
            try {
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                tl.remove();
                close(conn);
            }
        }
    }

    /**
     * 关闭释放连接，放回数据库连接池
     *
     * @param conn 数据库连接池中的一个数据库连接
     */
    public static void close(Connection conn) {
        DbUtils.closeQuietly(conn);
    }
}
