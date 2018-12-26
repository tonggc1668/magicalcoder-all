package com.magicalcoder.youyaboot.web.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.ConfigureRedisAction;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * 分布式session配置
 * @author www.magicalcoder.com
 * @date
 */
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 86400, redisNamespace="youyaboot_web_session")
public class DistributedSessionConfig {

	@Bean
	public static ConfigureRedisAction configureRedisAction() {
	    return ConfigureRedisAction.NO_OP;
	}

}
