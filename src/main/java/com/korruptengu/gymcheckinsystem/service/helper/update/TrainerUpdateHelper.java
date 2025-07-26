package com.korruptengu.gymcheckinsystem.service.helper.update;

import com.korruptengu.gymcheckinsystem.entity.Trainer;

public class TrainerUpdateHelper {
    public static void updateCompletely(Trainer target, Trainer source){
        target.setSpecialty(source.getSpecialty());
        target.setHireDate(source.getHireDate());
        target.setState(source.getState());
    }

    public static void updatePartially(Trainer target, Trainer source){
        if (source.getSpecialty() != null) target.setSpecialty(source.getSpecialty());
        if (source.getHireDate() != null) target.setHireDate(source.getHireDate());
        if (source.getState() != null) target.setState(source.getState());
    }

    public static boolean isAllFieldsNull(Trainer trainer){
        return (trainer.getSpecialty() == null &&
                trainer.getHireDate() == null &&
                trainer.getState() == null);
    }
}
