package com.zui.test.abs.factorytest.factory;

import com.zui.test.abs.factorytest.seal.OfficialSeal;

/**
 * @author zui
 */
public interface Provider {

    /**
     * 提供实体
     *
     * @return
     */
    OfficialSeal produce();
}
