package com.zui.test.abs.factorytest.factory;

import com.zui.test.abs.factorytest.seal.ChinaBankOfficialSeal;
import com.zui.test.abs.factorytest.seal.OfficialSeal;

/**
 * 中国银行公章工厂,用于获取公章
 *
 * @author zui
 */
public class ChinaBankOfficialSealFactory implements Provider {

    @Override
    public OfficialSeal produce() {
        return new ChinaBankOfficialSeal();
    }
}
