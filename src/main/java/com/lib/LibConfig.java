package com.lib;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
@Conditional(CustomCondition.class)
public class LibConfig {

    @Bean
    void testConfigImport(){
        log.info("lib config inject success");
    }
}
