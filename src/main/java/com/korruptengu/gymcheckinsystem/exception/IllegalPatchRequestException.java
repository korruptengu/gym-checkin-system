package com.korruptengu.gymcheckinsystem.exception;

import com.korruptengu.gymcheckinsystem.utils.StringUtils;

public class IllegalPatchRequestException extends RuntimeException{
    public IllegalPatchRequestException(String entityName){
        super("Patch" + StringUtils.capitalizeFirstLetter(entityName) +"Request darf nicht null sein.");
    }
}