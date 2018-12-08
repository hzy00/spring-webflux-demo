package com.hzy.spring.springwebflux.model;

import lombok.Data;
import org.springframework.http.codec.multipart.FilePart;

@Data
public class FileUploadReq {

    private FilePart file;

    private String memo;
}
