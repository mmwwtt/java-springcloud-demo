package com.mmwwtt.common.vo;

import lombok.Data;

@Data
public class ReturnResponse {
    private String code;
    private String msg;
    private Object data;

    public static ReturnResponse success() {
        ReturnResponse returnResponse = new ReturnResponse();
        returnResponse.setCode("200");
        return returnResponse;
    }

    public static ReturnResponse success(String msg, Object data) {
        ReturnResponse returnResponse = new ReturnResponse();
        returnResponse.setCode("200");
        returnResponse.setMsg(msg);
        returnResponse.setData(data);
        return returnResponse;
    }

    public static ReturnResponse error() {
        ReturnResponse returnResponse = new ReturnResponse();
        returnResponse.setCode("500");
        return returnResponse;
    }

    public static ReturnResponse error(String msg) {
        ReturnResponse returnResponse = new ReturnResponse();
        returnResponse.setCode("500");
        returnResponse.setMsg(msg);
        return returnResponse;
    }
}
