package com.korruptengu.gymcheckinsystem.util.update;

import com.korruptengu.gymcheckinsystem.entity.Member;

public class MemberUpdateHelper {
    public static void updateCompletely(Member target, Member source){
        target.setFirstname(source.getFirstname());
        target.setLastname(source.getLastname());
        target.setEMail(source.getEMail());
        target.setState(source.getState());
    }

    public static void updatePartially(Member target, Member source){
        if (source.getFirstname() != null) target.setFirstname(source.getFirstname());
        if (source.getLastname() != null) target.setLastname(source.getLastname());
        if (source.getEMail() != null) target.setEMail(source.getEMail());
        if (source.getState() != null) target.setState(source.getState());
    }

    public static boolean isAllFielddsNull(Member member) {
        return  member.getFirstname() == null &&
                member.getLastname() == null &&
                member.getEMail() == null &&
                member.getState() == null;
    }
}
