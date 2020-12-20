package cn.ilqjx.service;

import cn.ilqjx.pojo.User;

/**
 * @author upfly
 * @create 2020-11-30 14:48
 */
public interface UserService {

    /**
     * 检查用户名是否可用
     *
     * @param username 用户名
     * @return 如果用户名存在返回 true，否则返回 false。
     */
    boolean isExistForUsername(String username);

    /**
     * 注册用户
     *
     * @param user
     */
    void registUser(User user);

    /**
     * 登录
     *
     * @param user
     * @return 如果返回 null 说明登录失败，返回其他说明登录成功。
     */
    User login(User user);
}

