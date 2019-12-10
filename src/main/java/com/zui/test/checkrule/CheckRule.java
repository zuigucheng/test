package com.zui.test.checkrule;

import org.springframework.core.annotation.Order;

/**
 * @author zui
 * @date 2019.12.09 11:17
 * @since  校验规则接口
 */
public interface CheckRule {

    /**
     * 是否为空
     * @param object 对象
     * @return 非空返回true
     */
    @Order(1)
    boolean notEmpty(Object object);

    /**
     * 是否超过最大长度
     * @param str 传入字符串
     * @return 是则返回false
     */
    @Order(2)
    boolean maxSize(String str);

    /**
     * 是否超过最小长度
     * @param str 传入字符串
     * @return 是则返回false
     */
    @Order(3)
    boolean minSize(String str);

    /**
     * 是否为真
     * @param flags
     * @return
     */
    @Order(4)
    boolean isTrue(boolean flags);
}
