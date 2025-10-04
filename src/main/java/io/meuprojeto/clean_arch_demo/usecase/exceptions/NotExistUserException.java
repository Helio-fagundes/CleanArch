package io.meuprojeto.clean_arch_demo.usecase.exceptions;

public class NotExistUserException extends RuntimeException {

    public NotExistUserException(String message) {
        super(message);
    }
}
