package com.example.walkthroughjava.exception;

import lombok.Data;

@Data
public class SistemaException extends IllegalArgumentException {
    public String campo;

    public SistemaException(String s) {
        super(s);
    }

    public SistemaException(String message, Throwable cause) {
        super(message, cause);
    }

    public SistemaException(Throwable cause) {
        super(cause);
    }

    public SistemaException(String campo, String message) {
        super(message);
        this.campo = campo;
    }
}
