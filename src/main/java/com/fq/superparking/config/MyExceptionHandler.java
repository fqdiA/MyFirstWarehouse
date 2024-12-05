package com.fq.superparking.config;


import com.fq.superparking.common.R;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.annotation.Resource;
import java.util.concurrent.CompletableFuture;

/*
* 全局异常处理
*
* */
@RestControllerAdvice
public class MyExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public R<String> handlerIllegalArgumentException(Exception e){
        return R.fail(e.getMessage());
    }
}
