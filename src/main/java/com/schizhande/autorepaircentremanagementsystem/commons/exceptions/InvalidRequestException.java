package com.schizhande.autorepaircentremanagementsystem.commons.exceptions;

import lombok.Data;

@Data
public class InvalidRequestException extends RuntimeException {

    public InvalidRequestException(String s) {
        super(s);
    }

}
