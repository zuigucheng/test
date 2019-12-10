package com.zui.test.checkrule;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

/**
 * @author zui
 * @date 2019.12.09 12:04
 */
@Data
@ConfigurationProperties(prefix = "check.rule")
public class CheckRuleProperties {
    private String rules;
}
