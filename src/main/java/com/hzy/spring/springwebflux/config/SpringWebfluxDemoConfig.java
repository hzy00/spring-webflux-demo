package com.hzy.spring.springwebflux.config;

import com.lib.LibConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;

@Configuration
@Slf4j
@SpringBootApplication(scanBasePackages = "com.hzy.spring.springwebflux")
@Import(LibConfig.class)
public class SpringWebfluxDemoConfig {

    /**
     * 读取自定义配置
     */
    @Value("${custom.dev:hhh:默认值}")
    private String dev;

    @Bean
    void propertyTest(){
        log.info("测试配置读取的值：[{}],[{}]",dev);
    }

}
