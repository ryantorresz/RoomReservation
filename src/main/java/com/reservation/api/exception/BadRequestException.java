package com.reservation.api.exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String msg){super(msg); }
}
