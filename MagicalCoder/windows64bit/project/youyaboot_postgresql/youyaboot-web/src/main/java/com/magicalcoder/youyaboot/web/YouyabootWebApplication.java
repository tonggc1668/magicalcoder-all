package com.magicalcoder.youyaboot.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 *
 * @author www.magicalcoder.com
 * @date 2017-12-21
 */
@SpringBootApplication
@ComponentScan(basePackages = {
        "com.magicalcoder.youyaboot.core",
        "com.magicalcoder.youyaboot.service",
        "com.magicalcoder.youyaboot.web"
})
public class YouyabootWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(YouyabootWebApplication.class, args);
        }


    /**
     * 跨域过滤器
     * @return
     */
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(source);
    }

}
