package com.boot.bookstore.util;

import com.boot.bookstore.constant.ResultCode;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class ResultList implements Serializable {
    private Boolean success;
    private Integer code;
    private String message;
    private List<Object> data = new ArrayList();

    public ResultList(){}

    // 成功返回
    public static ResultList ok(){
        ResultList result = new ResultList();
        result.setSuccess(ResultCode.SUCCESS.getSuccess());
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result;
    }

    // 失败返回
    public static ResultList error(){
        ResultList result = new ResultList();
        result.setSuccess(ResultCode.FAILURE.getSuccess());
        result.setCode(ResultCode.FAILURE.getCode());
        result.setMessage(ResultCode.FAILURE.getMessage());
        return result;
    }

    // 失败返回
    public static ResultList notExist(){
        ResultList result = new ResultList();
        result.setSuccess(ResultCode.NOT_EXIST.getSuccess());
        result.setCode(ResultCode.NOT_EXIST.getCode());
        result.setMessage(ResultCode.NOT_EXIST.getMessage());
        return result;
    }

    // 设置返回结果
    public static ResultList setResult(ResultCode resultCode){
        ResultList result = new ResultList();
        result.setSuccess(resultCode.getSuccess());
        result.setCode(resultCode.getCode());
        result.setMessage(resultCode.getMessage());
        return result;
    }

    // 设置success状态
    public ResultList success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    // 设置code状态
    public ResultList code(Integer code){
        this.setCode(code);
        return this;
    }

    // 设置message状态
    public ResultList message(String message){
        this.setMessage(message);
        return this;
    }

    // 设置单值数据
    public ResultList data(Object obj){
        this.data.add(obj);
        return this;
    }

    // 设置多值数据
    public ResultList data(List list){
        this.setData(list);
        return this;
    }

}

