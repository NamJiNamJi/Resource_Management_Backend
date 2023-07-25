package com.douzone.wehago.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class Response {
    private HttpStatus status;
    private String message;
    private Object data;


    // Initial Setting
    public Response() {
        this.status = HttpStatus.BAD_REQUEST;
        this.message = null;
        this.data = null;
    }
}
