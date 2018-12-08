package com.hzy.spring.springwebflux.controller;

import com.hzy.spring.springwebflux.model.ConvertReq;
import com.hzy.spring.springwebflux.model.ConvertResp;
import com.hzy.spring.springwebflux.model.FileUploadReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
public class DemoController {

    @RequestMapping("test/exception")
    public Mono<Void> test() throws IllegalAccessException {
        log.info("receive exception req");
        throw new IllegalArgumentException("no access");
    }

    @RequestMapping("test/illegalAccess")
    public Mono<Void> testIllegalAccess() throws IllegalAccessException {
        log.info("receive IllegalAccessException req");
        throw new IllegalAccessException("no access");
    }

    @RequestMapping("test/fileUpload")
    public Mono<Void> testFileUpload(FileUploadReq fileUploadReq){
        log.info("receive file upload req:fileName={}",fileUploadReq.getFile().filename());
        return Mono.empty();
    }

    @PostMapping("test/convert")
    public Mono<ConvertResp> testConvert(@RequestBody ConvertReq convertReq){
        ConvertResp resp = new ConvertResp();
        resp.setCode(1);
        resp.setResp("success");
        return Mono.just(resp);
    }
}
