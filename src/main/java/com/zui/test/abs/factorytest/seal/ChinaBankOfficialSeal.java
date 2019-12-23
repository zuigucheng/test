package com.zui.test.abs.factorytest.seal;

/**
 * 中国银行公章
 *
 * @author 社会主义码农
 */
public class ChinaBankOfficialSeal implements OfficialSeal {
    @Override
    public void getOfficial() {
        System.out.println("This is the official seal of the Bank of China!");
    }
}
