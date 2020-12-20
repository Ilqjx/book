package cn.ilqjx.dao;

import cn.ilqjx.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author upfly
 * @create 2020-11-29 18:29
 */
public abstract class BaseDao<T> {
    private QueryRunner queryRunner = new QueryRunner();

    /**
     * 子类的 Class 实例
     */
    private Class<T> clazz;

    {
        Type type = this.getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) type;
        Type[] types = parameterizedType.getActualTypeArguments();
        clazz = (Class<T>) types[0];
    }

    /**
     * update() 方法用来执行：insert / delete / update
     *
     * @param sql 执行的 sql 语句
     * @param params sql 对应的参数
     * @return 如果返回 -1 代表操作失败，否则返回影响的行数。
     */
    public int update(String sql, Object... params) {
        Connection conn = JdbcUtils.getConnection();
        int count = -1;
        try {
            count = queryRunner.update(conn, sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
            // 1、如果前面的 jdbc 操作已经出现了异常，后面的就没有执行的必要了
            // 2、出现异常之后，TransactionFilter 需要捕捉异常从而进行事务的回滚
            throw new RuntimeException();
        }
        return count;
    }

    /**
     * 查询返回一个 JavaBean 的 sql 语句
     *
     * @param sql 执行的 sql 语句
     * @param params sql 对应的参数
     * @return 如果返回 null 代表没查询到，否则返回查询到的结果。
     */
    public T queryForOne(String sql, Object... params) {
        Connection conn = JdbcUtils.getConnection();
        T t = null;
        try {
            BeanHandler<T> handler = new BeanHandler<>(clazz);
            t = queryRunner.query(conn, sql, handler, params);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return t;
    }

    /**
     * 查询返回多个 JavaBean 的 sql 语句
     *
     * @param sql 执行的 sql 语句
     * @param params sql 对应的参数
     * @return 如果返回 null 代表没查询到，否则返回查询到的结果。
     */
    public List<T> queryForList(String sql, Object... params) {
        Connection conn = JdbcUtils.getConnection();
        List<T> result = null;
        try {
            BeanListHandler<T> handler = new BeanListHandler<>(clazz);
            result = queryRunner.query(conn, sql, handler, params);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return result;
    }

    /**
     * 查询返回一行一列的 sql 语句
     *
     * @param sql 执行的 sql 语句
     * @param params sql 对应的参数值
     * @param <E> 返回的类型的泛型
     * @return 如果返回 null 代表没查询到，否则返回查询到的结果。
     */
    public <E> E queryForSingleValue(String sql, Object... params) {
        Connection conn = JdbcUtils.getConnection();
        E e = null;
        try {
            ScalarHandler handler = new ScalarHandler();
            e = (E) queryRunner.query(conn, sql, handler, params);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException();
        }
        return e;
    }
}
