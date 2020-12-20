package cn.ilqjx.dao;

import cn.ilqjx.pojo.User;

/**
 * @author upfly
 * @create 2020-11-30 13:04
 */
public interface UserDao {

    /**
     * 保存用户信息
     *
     * @param user
     * @return 如果返回 -1 说明保存失败，其他是 sql 语句影响的行数。
     */
    int saveUser(User user);

    /**
     * 根据用户名查询用户信息
     *
     * @param username 用户名
     * @return 如果返回 null 说明用户名不存在，否则返回用户信息。
     */
    User queryUserByUsername(String username);

    /**
     * 根据用户名和密码查询用户信息
     *
     * @param username 用户名
     * @param password 密码
     * @return 如果返回 null 说明用户名或密码错误，否则返回用户信息。
     */
    User queryUserByUsernameAndPassword(String username, String password);
}
