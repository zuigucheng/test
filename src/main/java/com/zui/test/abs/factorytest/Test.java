package com.zui.test.abs.factorytest;

import com.zui.test.abs.factorytest.factory.ChinaBankOfficialSealFactory;
import com.zui.test.abs.factorytest.factory.ConstructionBankOfficialSealFactory;
import com.zui.test.abs.factorytest.factory.Provider;
import com.zui.test.abs.factorytest.seal.OfficialSeal;

/**
 * 测试步骤
 * 1.获取工厂类,所有工厂类都实现了Provider接口,此接口对象可以代表所有工厂类
 * 2.获取具体对象,所有公章都实现了OfficialSeal接口,此接口对象可以代表所有公章类
 * 3.由接口对象执行公共方法
 * <p>
 * 工厂拓展,例:添加工商银行
 * 1.编写实体类,写一个BusinessBank,并实现OfficialSeal接口,重写其方法
 * 2.编写工厂类,写一个BusinessBankFactory,并实现Provider接口,重写其方法
 * 3.利用下述逻辑即可获得具体对象
 *
 * @author zui
 */
public class Test {

    public static void main(String[] args) {
        /*获取中国银行公章*/
        Provider provider = new ChinaBankOfficialSealFactory();
        OfficialSeal sender = provider.produce();
        sender.getOfficial();

        /*获取建设银行公章*/
        Provider provider1 = new ConstructionBankOfficialSealFactory();
        OfficialSeal produce = provider1.produce();
        produce.getOfficial();
    }
}
