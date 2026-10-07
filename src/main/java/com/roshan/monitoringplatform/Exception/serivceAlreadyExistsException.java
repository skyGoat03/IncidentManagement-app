package com.roshan.monitoringplatform.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class serivceAlreadyExistsException extends RuntimeException {

    public serivceAlreadyExistsException(String message){
        super(message);
    }
}
