package com.boot.bookstore.constant;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum ResultCode {

    FAILURE(false, -1, "failure"),
    NOT_EXIST(false, 0, "not exist"),
    SUCCESS(true, 1, "success"),
    PARAM_IS_INVALID(false, 2, "param is invalid");

    Boolean success;
    Integer code;
    String message;

    ResultCode(Boolean success, Integer code, String message){
        this.success = success;
        this.code = code;
        this.message = message;
    }

//    public static ResultCode getResultCode(Integer code){
//        for (ResultCode resultCode : values()){
//            if (code == resultCode.code){
//                return resultCode;
//            }
//        }
//        return DEFAULT;
//    }
}
