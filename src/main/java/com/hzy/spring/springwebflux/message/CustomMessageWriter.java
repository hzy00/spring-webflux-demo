package com.hzy.spring.springwebflux.message;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.core.ResolvableType;
import org.springframework.core.annotation.Order;
import org.springframework.core.codec.Encoder;
import org.springframework.http.MediaType;
import org.springframework.http.ReactiveHttpInputMessage;
import org.springframework.http.ReactiveHttpOutputMessage;
import org.springframework.http.codec.EncoderHttpMessageWriter;
import org.springframework.http.codec.HttpMessageReader;
import org.springframework.http.codec.HttpMessageWriter;
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
public class CustomMessageWriter extends EncoderHttpMessageWriter<Object> {


    /**
     * Create an instance wrapping the given {@link Encoder}.
     *
     * @param
     */
    public CustomMessageWriter() {
        super(new Jackson2JsonEncoder());
    }

    /**
     * 增加 && mediaType != null 使系统判定为Typed Writer
     * @param elementType
     * @param mediaType
     * @return
     */
    @Override
    public boolean canWrite(ResolvableType elementType, MediaType mediaType) {
        return super.canWrite(elementType, mediaType) && mediaType != null;
    }

    @Override
    public Mono<Void> write(Publisher<?> inputStream, ResolvableType elementType, MediaType mediaType, ReactiveHttpOutputMessage message, Map<String, Object> hints) {
        log.info("custom write");
        return super.write(inputStream, elementType, mediaType, message, hints);
    }

    @Override
    public Mono<Void> write(Publisher<?> inputStream, ResolvableType actualType, ResolvableType elementType, MediaType mediaType, ServerHttpRequest request, ServerHttpResponse response, Map<String, Object> hints) {
        log.info("custom write");
        return super.write(inputStream, actualType, elementType, mediaType, request, response, hints);
    }
}
