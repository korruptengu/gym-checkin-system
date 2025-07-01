package com.korruptengu.gymcheckinsystem.service.helper.update;

import com.korruptengu.gymcheckinsystem.entity.Member;

public class MemberUpdateHelper {
    public static void updateCompletely(Member target, Member source){
        target.setFirstname(source.getFirstname());
        target.setLastname(source.getLastname());
        target.setEmail(source.getEmail());
        target.setState(source.getState());
    }

    public static void updatePartially(Member target, Member source){
        if (source.getFirstname() != null) target.setFirstname(source.getFirstname());
        if (source.getLastname() != null) target.setLastname(source.getLastname());
        if (source.getEmail() != null) target.setEmail(source.getEmail());
        if (source.getState() != null) target.setState(source.getState());
    }

    public static boolean isAllFieldsNull(Member member) {
        return  member.getFirstname() == null &&
                member.getLastname() == null &&
                member.getEmail() == null &&
                member.getState() == null;
    }
}
