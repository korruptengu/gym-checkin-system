package com.korruptengu.gymcheckinsystem.service.helper.update;

import com.korruptengu.gymcheckinsystem.entity.CheckIn;

public class CheckInUpdateHelper {
    public static void updateCompletely(CheckIn target, CheckIn source){
        target.setCheckInTime(source.getCheckInTime());
        target.setCheckOutTime(source.getCheckOutTime());
        target.setMember(source.getMember());
    }

    public static void updatePartially(CheckIn target, CheckIn source){
        if (source.getCheckInTime() != null) target.setCheckInTime(source.getCheckInTime());
        if (source.getCheckOutTime() != null) target.setCheckOutTime(source.getCheckOutTime());
        if (source.getMember() != null) target.setMember(source.getMember());
    }

    public static boolean isAllFieldsNull(CheckIn checkIn) {
        return  checkIn.getMember() == null &&
                checkIn.getCheckInTime() == null &&
                checkIn.getCheckOutTime() == null;
    }
}
