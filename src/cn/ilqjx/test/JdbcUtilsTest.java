package cn.ilqjx.test;

import cn.ilqjx.utils.JdbcUtils;
import org.junit.Test;

import java.sql.Connection;

/**
 * @author upfly
 * @create 2020-11-19 18:16
 */
public class JdbcUtilsTest {

    @Test
    public void testJdbcUtils() {
        int num = 100;
        for (int i = 0; i < num; i++) {
            Connection conn = JdbcUtils.getConnection();
            System.out.println(conn);
            JdbcUtils.close(conn);
            System.out.println(conn);
        }
    }
}
