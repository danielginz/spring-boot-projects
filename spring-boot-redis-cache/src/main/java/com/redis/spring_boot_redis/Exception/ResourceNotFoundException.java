package com.redis.spring_boot_redis.Exception;

public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 763040204220054875L;

    public ResourceNotFoundException() {
        super();
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(Throwable cause) {
        super(cause);
    }

}