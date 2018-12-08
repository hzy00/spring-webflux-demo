package com.hzy.spring.springwebflux.message;

import com.hzy.spring.springwebflux.model.ConvertReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ResolvableType;
import org.springframework.core.codec.Decoder;
import org.springframework.http.MediaType;
import org.springframework.http.ReactiveHttpInputMessage;
import org.springframework.http.codec.DecoderHttpMessageReader;
import org.springframework.http.codec.HttpMessageReader;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
public class CustomMessageReader extends DecoderHttpMessageReader<Object> {


    /**
     * Create an instance wrapping the given {@link Decoder}.
     *
     * @param
     */
    public CustomMessageReader() {
        super(new Jackson2JsonDecoder());
    }

    /**
     * 增加 && mediaType != null 使系统判定为Typed Reader
     * @param elementType
     * @param mediaType
     * @return
     */
    @Override
    public boolean canRead(ResolvableType elementType, MediaType mediaType) {
        return super.canRead(elementType, mediaType) && mediaType != null;
    }

    @Override
    public Flux<Object> read(ResolvableType elementType, ReactiveHttpInputMessage message, Map<String, Object> hints) {
        log.info("custom read");
        return super.read(elementType, message, hints);
    }

    @Override
    public Flux<Object> read(ResolvableType actualType, ResolvableType elementType, ServerHttpRequest request, ServerHttpResponse response, Map<String, Object> hints) {
        log.info("custom read");
        return super.read(actualType, elementType, request, response, hints).map(item -> {
            if(item instanceof ConvertReq){
                log.info("intercept ConvertReq");
                //do something
            }
            return item;
        });
    }

    @Override
    public Mono<Object> readMono(ResolvableType actualType, ResolvableType elementType, ServerHttpRequest request, ServerHttpResponse response, Map<String, Object> hints) {
        log.info("custom read");
        return super.readMono(actualType, elementType, request, response, hints).map(item -> {
            if(item instanceof ConvertReq){
                log.info("intercept ConvertReq");
                //do something
            }
            return item;
        });
    }
}
