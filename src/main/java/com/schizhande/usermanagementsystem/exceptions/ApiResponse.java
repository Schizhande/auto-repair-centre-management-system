/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.schizhande.usermanagementsystem.exceptions;

import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;


@Builder
public class ApiResponse {

    private HttpStatus status;
    private String message;
    private Object content;


    public ApiResponse(HttpStatus status, String message, Object content) {
        super();
        this.status = status;
        this.message = message;
        this.content = content;
    }

    public ApiResponse(HttpStatus status, String message, String content) {
        super();
        this.status = status;
        this.message = message;
        this.content = Arrays.asList(content);
    }

    public ApiResponse(HttpStatus status, String message) {
        super();
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(List<String> content) {
        this.content = content;
    }

}