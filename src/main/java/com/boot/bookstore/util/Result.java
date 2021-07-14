package com.boot.bookstore.util;

import com.boot.bookstore.constant.ResultCode;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
public class Result implements Serializable {
    private Boolean success;
    private Integer code;
    private String message;
    private Map<String, Object> data = new HashMap<String, Object>();

    public Result(){}

    // 成功返回
    public static Result ok(){
        Result result = new Result();
        result.setSuccess(ResultCode.SUCCESS.getSuccess());
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result;
    }

    // 失败返回
    public static Result error(){
        Result result = new Result();
        result.setSuccess(ResultCode.FAILURE.getSuccess());
        result.setCode(ResultCode.FAILURE.getCode());
        result.setMessage(ResultCode.FAILURE.getMessage());
        return result;
    }

    // 设置返回结果
    public static Result setResult(ResultCode resultCode){
        Result result = new Result();
        result.setSuccess(resultCode.getSuccess());
        result.setCode(resultCode.getCode());
        result.setMessage(resultCode.getMessage());
        return result;
    }

    // 设置success状态
    public Result success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    // 设置code状态
    public Result code(Integer code){
        this.setCode(code);
        return this;
    }

    // 设置message状态
    public Result message(String message){
        this.setMessage(message);
        return this;
    }

    // 设置单值数据
    public Result data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    // 设置多值数据
    public Result data(Map<String, Object> map){
        this.setData(map);
        return this;
    }

}
