package com.korruptengu.gymcheckinsystem.service.helper.update;

import com.korruptengu.gymcheckinsystem.entity.CourseType;

public class CourseTypeUpdateHelper {
    public static void updateCompletely(CourseType target, CourseType source){
        target.setTitle(source.getTitle());
        target.setCategory(source.getCategory());
        target.setDescription(source.getDescription());
    }

    public static void updatePartially(CourseType target, CourseType source){
        if (source.getTitle() != null) target.setTitle(source.getTitle());
        if (source.getCategory() != null) target.setCategory(source.getCategory());
        if (source.getDescription() != null) target.setDescription(source.getDescription());
    }

    public static boolean isAllFieldsNull(CourseType courseType) {
        return  courseType.getTitle() == null &&
                courseType.getCategory() == null &&
                courseType.getDescription() == null;
    }
}
