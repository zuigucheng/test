package com.zui.test.checkrule;

/**
 * @author zui
 * @date 2019.12.09 11:35
 * @since 模板方法
 */
public abstract class AbstractCheckRule implements CheckRule {

    private static final int MAX_LENGTH = 10;
    private static final int MIN_LENGTH = 3;

    @Override
    public boolean notEmpty(Object object) {
        return object == null ? false : true;
    }

    @Override
    public boolean maxSize(String str) {
        return str.length() > MAX_LENGTH ? false : true;
    }

    @Override
    public boolean minSize(String str) {
        return str.length() < MIN_LENGTH ? false : true;
    }
}
