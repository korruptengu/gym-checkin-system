package com.korruptengu.gymcheckinsystem.service.helper.update;

import com.korruptengu.gymcheckinsystem.entity.AppUser;
import com.korruptengu.gymcheckinsystem.entity.Member;

public class MemberUpdateHelper {
    public static void updateCompletely(Member target, Member source){
        target.setJoinDate(source.getJoinDate());
        target.setState(source.getState());
    }

    public static void updatePartially(Member target, Member source){
        if (source.getJoinDate() != null) target.setJoinDate(source.getJoinDate());
        if (source.getState() != null) target.setState(source.getState());
    }

    public static boolean isAllFieldsNull(Member member) {
        return  member.getJoinDate() == null &&
                member.getState() == null;
    }
}
