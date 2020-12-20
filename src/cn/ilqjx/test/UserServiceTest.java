package cn.ilqjx.test;

import cn.ilqjx.pojo.User;
import cn.ilqjx.service.UserService;
import cn.ilqjx.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author upfly
 * @create 2020-11-30 21:23
 */
public class UserServiceTest {
    private UserService userService = new UserServiceImpl();

    @Test
    public void isExistForUsername() {
        System.out.println(userService.isExistForUsername("ggg"));
    }

    @Test
    public void registUser() {
        userService.registUser(new User(null, "郭振伟", "guozhenwei", "guozhenwei@163.com"));
    }

    @Test
    public void login() {
        System.out.println(userService.login(new User(null, "郭振伟", "guzhenwei", null)));
    }
}