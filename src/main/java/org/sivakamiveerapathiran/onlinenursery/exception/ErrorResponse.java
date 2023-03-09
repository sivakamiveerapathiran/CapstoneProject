package org.sivakamiveerapathiran.onlinenursery.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class ErrorResponse {
    private int statusCode;
    private String message;
    public ErrorResponse(){

    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ErrorResponse(String message) {
        super();
        this.message = message;
    }
}
