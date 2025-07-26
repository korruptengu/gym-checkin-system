package com.korruptengu.gymcheckinsystem.exception;

import com.korruptengu.gymcheckinsystem.utils.StringUtils;

public class IllegalPutRequestException extends RuntimeException{
    public IllegalPutRequestException(String entityName){
        super("Put" + StringUtils.capitalizeFirstLetter(entityName) +"Request darf nicht null sein.");
    }
}