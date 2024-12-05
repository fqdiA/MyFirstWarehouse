package com.fq.superparking.common;

import lombok.Data;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * 统一响应对象
 *
 * @author fang
 * @date 2023/10/16
 */
@Data
public class R <T>{
    private Integer code;
    private String message;
    private T data;

    private R(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }





    public R() {
    }


    //请求成功状态码
    public static <T> R<T> ok(Integer code, String message, T data){
        return new R<>(code,message,data);
    }

    public static <T> R<T> ok(T data){
        return new R<>(200,"请求成功",data);
    }

    public static <T> R<T> ok(){
        return ok(null);
    }

    //请求失败状态码
    public static <T> R<T> fail(Integer code,String message,T data){
        return new R<>(code,message,data);
    }

    public static <T> R<T> fail(T data){
        return new R<>(0,"请求失败",data);
    }

    public static <T> R<T> fail(){
        return fail(null);
    }



}