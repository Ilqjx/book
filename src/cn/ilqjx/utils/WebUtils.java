package cn.ilqjx.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author upfly
 * @create 2020-12-07 19:31
 */
public class WebUtils {

    /**
     * 将 Map 中的信息注入到相应的 JavaBean 属性中
     *
     * @param value 携带 bean 信息的 Map
     * @param bean 要注入信息的 bean
     * @param <T> bean 的类型
     * @return 注入信息后的 bean
     */
    public static <T> T copyParamToBean(Map value, T bean) {
        try {
            BeanUtils.populate(bean, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

    /**
     * 将字符串数据转换为 int 型数据
     *
     * @param strInt 字符串数据
     * @param defaultValue 默认值
     * @return 如果转换成功则返回转换后的 int 型数据，否则返回默认值。
     */
    public static int parseInt(String strInt, int defaultValue) {
        try {
            return Integer.parseInt(strInt);
        } catch (Exception e) {
            // e.printStackTrace();
        }
        return defaultValue;
    }

    /**
     * 将字符串数据转换为 BigDecimal 类型的数据
     *
     * @param strBigDecimal 字符串数据
     * @param defaultValue 默认值
     * @return 如果转换成功，则返回转换后的 BigDecimal 类型的数据，否则返回默认值。
     */
    public static BigDecimal parseBigDecimal(String strBigDecimal, BigDecimal defaultValue) {
        try {
            return new BigDecimal(strBigDecimal);
        } catch (Exception e) {
            // e.printStackTrace();
        }
        return defaultValue;
    }
}
