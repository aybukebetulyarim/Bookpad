package com.library.bookpad.resource.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse<T> {
    private String message;
    private T data;
    private Integer status;
    private String errorCode;
}
