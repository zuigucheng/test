package com.zui.test.abs.factorytest.factory;

import com.zui.test.abs.factorytest.seal.ConstructionBankOfficialSeal;
import com.zui.test.abs.factorytest.seal.OfficialSeal;

/**
 * 获取建设银行公章
 *
 * @author zui
 */
public class ConstructionBankOfficialSealFactory implements Provider {

    @Override
    public OfficialSeal produce() {
        return new ConstructionBankOfficialSeal();
    }
}
