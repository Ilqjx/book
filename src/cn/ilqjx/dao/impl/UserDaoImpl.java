package cn.ilqjx.dao.impl;

import cn.ilqjx.dao.BaseDao;
import cn.ilqjx.dao.UserDao;
import cn.ilqjx.pojo.User;

/**
 * @author upfly
 * @create 2020-11-30 13:16
 */
public class UserDaoImpl extends BaseDao<User> implements UserDao {

    @Override
    public int saveUser(User user) {
        String sql = "insert into t_user(`username`, `password`, `email`) values(?, ?, ?)";
        int count = update(sql, user.getUsername(), user.getPassword(), user.getEmail());
        return count;
    }

    @Override
    public User queryUserByUsername(String username) {
        String sql = "select `id`, `username`, `password`, `email` from t_user where username = ?";
        User user = queryForOne(sql, username);
        return user;
    }

    @Override
    public User queryUserByUsernameAndPassword(String username, String password) {
        String sql = "select `id`, `username`, `password`, `email` from t_user where username = ? and password = ?";
        User user = queryForOne(sql, username, password);
        return user;
    }
}
