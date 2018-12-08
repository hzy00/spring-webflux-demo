package com.hzy.spring.springwebflux.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.reactive.result.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Component
public class CustomWebFilter implements WebFilter {

    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        Object handlerMethod = requestMappingHandlerMapping.getHandler(exchange).toProcessor().peek();
        //注意跨域时的配置，跨域时浏览器会先发送一个option请求，这时候getHandler不会时真正的HandlerMethod
        if(handlerMethod instanceof HandlerMethod){
            Valid valid = ((HandlerMethod) handlerMethod).getMethodAnnotation(Valid.class);
            //do your logic
        }
        //preprocess()
        Mono<Void> response = chain.filter(exchange);
        //postprocess()
        return response;
    }
}
