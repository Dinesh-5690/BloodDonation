package com.blood.util;

public class ValidationException extends Exception {
    @Override
    public String toString() {
        return "Invalid Input or validation fails";
    }
}
