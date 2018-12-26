package com.magicalcoder.youyaboot.web.common.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created by magicalcoder.com on
 * 当spring bean 启动完毕 就会执行此类方法
 */
@Component
@Slf4j
public class SpringBeanAllInitFinished implements ApplicationListener<ApplicationReadyEvent> {

    @Override
    public void onApplicationEvent(ApplicationReadyEvent contextRefreshedEvent) {
            //需要执行的逻辑代码，当spring容器初始化完成后就会执行该方法。
           log.info("spring bean 加载完毕");
        }
    }
