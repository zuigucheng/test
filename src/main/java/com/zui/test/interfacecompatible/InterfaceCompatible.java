package com.zui.test.interfacecompatible;

import lombok.Data;

/**
 * @author zui
 * @date 2019.12.09 16:57
 */
public interface InterfaceCompatible {

    User getUser(UserParam userParam);
    User getUserById(Integer id);

}
