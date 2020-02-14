package com.schizhande.usermanagementsystem.exceptions;

import lombok.Data;

@Data
public class InvalidRequestException extends RuntimeException {

    public InvalidRequestException(String s) {
        super(s);
    }

}
