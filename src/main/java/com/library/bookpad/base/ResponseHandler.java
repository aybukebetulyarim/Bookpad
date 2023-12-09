package com.library.bookpad.base;

import com.library.bookpad.enums.ExceptionEnum;
import com.library.bookpad.resource.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ResponseHandler {
    public static <T> BaseResponse<T> responseEntityWrapper(HttpStatus responseCode, T responseObject, String message) {
        BaseResponse<T> response = new BaseResponse<>();
        response.setMessage(message);
        response.setStatus(responseCode.value());
        response.setData(responseObject);
        return response;
    }

    public static <T> BaseResponse<T> responseEntityWrapper(HttpStatus responseCode, T responseObject, ExceptionEnum exceptionEnum) {
        BaseResponse<T> response = new BaseResponse<>();
        response.setMessage(exceptionEnum.getCode());
        response.setStatus(responseCode.value());
        response.setData(responseObject);
        return response;
    }

    public static <T> BaseResponse<T> responseEntityWrapper(HttpStatus responseCode, T responseObject) {
        BaseResponse<T> response = new BaseResponse<>();
        response.setStatus(responseCode.value());
        response.setData(responseObject);
        return response;
    }


}
