package com.magicalcoder.youyaboot.web.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * author: www.magicalcoder.com
 * date:2018/5/12
 * function:监控配置
 */
@Configuration
public class MonitorConfig {
    @Value("${spring.monitor.druid.userName}")
    private String druidUserName;
    @Value("${spring.monitor.druid.password}")
    private String druidPassword;
    @Value("${spring.monitor.druid.allowIps}")
    private String druidAllowIps;
    @Value("${spring.monitor.druid.denyIps}")
    private String druidDenyIps;
    @Value("${spring.monitor.druid.filters}")
    private String druidFilters;

    public String getDruidUserName() {
        return druidUserName;
    }

    public String getDruidPassword() {
        return druidPassword;
    }

    public String getDruidAllowIps() {
        return druidAllowIps;
    }

    public String getDruidDenyIps() {
        return druidDenyIps;
    }

    public String getDruidFilters() {
        return druidFilters;
    }
}
