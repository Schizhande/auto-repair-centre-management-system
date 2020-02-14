package com.schizhande.usermanagementsystem.exceptions;

public class RecordExistsException extends RuntimeException {
    public RecordExistsException(String message) {
        super(message);
    }
}

