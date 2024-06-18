package com.colab.restaurant.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message);
    }
}
