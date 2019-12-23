package com.zui.test.abs.factorytest.seal;

/**
 * 建设银行公章
 *
 * @author zui
 */
public class ConstructionBankOfficialSeal implements OfficialSeal {

    @Override
    public void getOfficial() {
        System.out.println("this is official seal of the Bank of Construction!");
    }
}
