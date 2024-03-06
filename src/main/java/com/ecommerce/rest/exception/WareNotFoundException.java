package com.ecommerce.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class WareNotFoundException extends ResourceNotFoundException {
    public WareNotFoundException(String message) {
        super(message);
    }
}
