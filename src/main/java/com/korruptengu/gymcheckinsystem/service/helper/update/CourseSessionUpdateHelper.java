package com.korruptengu.gymcheckinsystem.service.helper.update;

import com.korruptengu.gymcheckinsystem.entity.CourseSession;

public class CourseSessionUpdateHelper {
    public static void updateCompletely(CourseSession target, CourseSession source){
        target.setStartTime(source.getStartTime());
        target.setDurationInMinutes(source.getDurationInMinutes());
        target.setCourseType(source.getCourseType());
        target.setTrainer(source.getTrainer());
    }

    public static void updatePartially(CourseSession target, CourseSession source){
        if (source.getStartTime() != null) target.setStartTime(source.getStartTime());
        if (source.getDurationInMinutes() != null) target.setDurationInMinutes(source.getDurationInMinutes());
        if (source.getCourseType() != null) target.setCourseType(source.getCourseType());
        if (source.getTrainer() != null) target.setTrainer(source.getTrainer());
    }

    public static boolean isAllFieldsNull(CourseSession session) {
        return  session.getDurationInMinutes() == null &&
                session.getStartTime() == null &&
                session.getCourseType() == null &&
                session.getTrainer() == null;
    }

    public static CourseSession buildMergeCourseSession(CourseSession existing, CourseSession partialUpdate){
            return new CourseSession(
                    partialUpdate.getStartTime() != null
                    ? partialUpdate.getStartTime()
                            : existing.getStartTime(),
                    partialUpdate.getDurationInMinutes() != null
                            ? partialUpdate.getDurationInMinutes()
                            : existing.getDurationInMinutes(),
                    partialUpdate.getCourseType() != null
                            ? partialUpdate.getCourseType()
                            : existing.getCourseType(),
                    partialUpdate.getTrainer() != null
                            ? partialUpdate.getTrainer()
                            : existing.getTrainer()
            );
    }
}