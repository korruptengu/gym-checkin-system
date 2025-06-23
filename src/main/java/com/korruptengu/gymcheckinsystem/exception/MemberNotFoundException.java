package com.korruptengu.gymcheckinsystem.exception;

public class MemberNotFoundException extends RuntimeException {

    public MemberNotFoundException(Long id){
        super("Member with Id: " + id + " not found");
    }
}
