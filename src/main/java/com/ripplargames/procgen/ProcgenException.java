package com.ripplargames.procgen;

public class ProcgenException extends RuntimeException {
    public ProcgenException() {
    }

    public ProcgenException(String message) {
        super(message);
    }

    public ProcgenException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProcgenException(Throwable cause) {
        super(cause);
    }
}
