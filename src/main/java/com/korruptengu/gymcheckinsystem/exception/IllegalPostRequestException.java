package com.korruptengu.gymcheckinsystem.exception;

import com.korruptengu.gymcheckinsystem.utils.StringUtils;

public class IllegalPostRequestException extends RuntimeException{
    public IllegalPostRequestException(String entityName){
        super("Post" + StringUtils.capitalizeFirstLetter(entityName) +"Request darf nicht null sein.");
    }
}