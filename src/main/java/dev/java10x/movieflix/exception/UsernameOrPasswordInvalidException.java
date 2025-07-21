package dev.java10x.movieflix.exception;

public class UsernameOrPasswordInvalidException extends RuntimeException {
    public  UsernameOrPasswordInvalidException(String message) {
        super(message);
    }
}
