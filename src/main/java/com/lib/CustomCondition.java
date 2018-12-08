package com.lib;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

@Slf4j
public class CustomCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String match = context.getEnvironment().getProperty("custom.condition","false");
        log.info("loader custom condition config:[{}]",match);
        return "true".equals(match);
    }
}
