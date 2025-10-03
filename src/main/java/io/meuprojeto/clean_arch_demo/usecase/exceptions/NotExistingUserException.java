package io.meuprojeto.clean_arch_demo.usecase.exceptions;

public class NotExistingUserException extends RuntimeException {

    public NotExistingUserException(String message) {
        super(message);
    }
}
