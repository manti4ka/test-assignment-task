package org.test.assignment.custom;

public class ValidationException extends  Exception {

    public ValidationException(String errorMessage) {
        super(errorMessage);
    }
}
