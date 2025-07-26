package com.korruptengu.gymcheckinsystem.service.helper.update;

import com.korruptengu.gymcheckinsystem.entity.AppUser;

public class AppUserUpdateHelper {

    public static void updateCompletely(AppUser target, AppUser source){
        target.setUsername(source.getUsername());
        target.setPassword(source.getPassword());
        target.setURole(source.getURole());
        target.setFirstname(source.getFirstname());
        target.setLastname(source.getLastname());
        target.setEmail(source.getEmail());
        target.setMember(source.getMember());
        target.setTrainer(source.getTrainer());
    }

    public static void updatePartially(AppUser target, AppUser source){
        if (source.getPassword() != null) target.setPassword(source.getPassword());
        if (source.getURole() != null) target.setURole(source.getURole());
        if (source.getFirstname() != null) target.setFirstname(source.getFirstname());
        if (source.getLastname() != null) target.setLastname(source.getLastname());
        if (source.getEmail() != null) target.setEmail(source.getEmail());
        if (source.getMember() != null) target.setMember(source.getMember());
        if (source.getTrainer() != null) target.setTrainer(source.getTrainer());
    }


    public static boolean isAllFieldsNull(AppUser appUser) {
        return  appUser.getPassword() == null &&
                appUser.getURole() == null &&
                appUser.getFirstname() == null &&
                appUser.getLastname() == null &&
                appUser.getEmail() == null &&
                appUser.getTrainer() == null &&
                appUser.getMember() == null;
    }
}
