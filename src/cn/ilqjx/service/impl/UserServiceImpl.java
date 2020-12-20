package cn.ilqjx.service.impl;

import cn.ilqjx.dao.UserDao;
import cn.ilqjx.dao.impl.UserDaoImpl;
import cn.ilqjx.pojo.User;
import cn.ilqjx.service.UserService;

/**
 * @author upfly
 * @create 2020-11-30 14:48
 */
public class UserServiceImpl implements UserService {
    private UserDao userDAO = new UserDaoImpl();

    @Override
    public boolean isExistForUsername(String username) {
        if (userDAO.queryUserByUsername(username) == null) {
            return false;
        }
        return true;
    }

    @Override
    public void registUser(User user) {
        userDAO.saveUser(user);
    }

    @Override
    public User login(User user) {
        return userDAO.queryUserByUsernameAndPassword(user.getUsername(), user.getPassword());
    }
}
