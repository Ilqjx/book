package cn.ilqjx.test;

import cn.ilqjx.dao.UserDao;
import cn.ilqjx.dao.impl.UserDaoImpl;
import cn.ilqjx.pojo.User;
import org.junit.Test;

/**
 * @author upfly
 * @create 2020-11-30 19:40
 */
public class UserDaoTest {
    private UserDao userDAO = new UserDaoImpl();

    @Test
    public void saveUser() {
        userDAO.saveUser(new User(null, "gzw", "abc123", "gzw@163.com"));
    }

    @Test
    public void queryUserByUsername() {
        System.out.println(userDAO.queryUserByUsername("gzw1"));
    }

    @Test
    public void queryUserByUsernameAndPassword() {
        System.out.println(userDAO.queryUserByUsernameAndPassword("gzw1", "abc123"));
    }
}