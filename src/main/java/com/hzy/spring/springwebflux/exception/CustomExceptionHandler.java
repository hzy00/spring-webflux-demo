package com.hzy.spring.springwebflux.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String convertExceptionMsg(Exception e){
        //自定义逻辑，可返回其他值
        return "error";
    }

    @ExceptionHandler(IllegalAccessException.class)
    public Mono<String> convertIllegalAccessError(Exception e){
        //自定义逻辑，可返回其他值
        return Mono.just("illegal access");
    }
}
