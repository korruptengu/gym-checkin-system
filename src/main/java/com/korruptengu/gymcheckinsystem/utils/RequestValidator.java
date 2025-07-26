package com.korruptengu.gymcheckinsystem.utils;

import com.korruptengu.gymcheckinsystem.exception.IllegalPatchRequestException;
import com.korruptengu.gymcheckinsystem.exception.IllegalPostRequestException;
import com.korruptengu.gymcheckinsystem.exception.IllegalPutRequestException;

public class RequestValidator {
    public static void requireNonNull(Object request, String requestName, String entityName) {
        if (request == null) {
            if ("post".equalsIgnoreCase(requestName)) throw new IllegalPutRequestException(entityName);
            if ("put".equalsIgnoreCase(requestName)) throw new IllegalPostRequestException(entityName);
            if ("patch".equalsIgnoreCase(requestName))  throw new IllegalPatchRequestException(entityName);
        }
        throw new IllegalArgumentException("Nicht unterstützter Request-Typ: " + requestName);
    }
}
