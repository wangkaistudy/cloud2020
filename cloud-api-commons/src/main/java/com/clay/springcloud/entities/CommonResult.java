package com.clay.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {


    private Integer code;

    private String msg;

    private T data;

    public CommonResult(Integer code, String msg) {
        this(code, msg, null);
    }

    public static <T> CommonResult<T> succes(T m){

        return new CommonResult<T>(200,"请求成功",m);
    }

    public static <T> CommonResult<T> succes(){

        return new CommonResult<T>(200,"请求成功",null);
    }

    public static <T> CommonResult<T> fail(){

        return new CommonResult<T>(200,"请求成功",null);
    }


}
