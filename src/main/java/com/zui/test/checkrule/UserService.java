package com.zui.test.checkrule;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * @author zui
 * @date 2019.12.09 11:39
 */
public class UserService extends AbstractCheckRule {

    @Override
    public boolean isTrue(boolean flags) {
        return flags;
    }

    public Integer insertUser(User user){
        if (user == null){
            //添加逻辑
            return 1;
        }
        return 0;
    }
}
