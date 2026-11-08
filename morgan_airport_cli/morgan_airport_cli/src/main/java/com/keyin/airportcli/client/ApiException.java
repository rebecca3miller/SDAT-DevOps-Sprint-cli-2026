package com.keyin.airportcli.client;

public class ApiException extends RuntimeException {
    public ApiException(String message) {
        super(message);
    }
}
