package com.korruptengu.gymcheckinsystem.service.helper.update;

import com.korruptengu.gymcheckinsystem.entity.Trainer;

public class TrainerUpdateHelper {
    public static void updateCompletely(Trainer target, Trainer source){
        target.setFirstname(source.getFirstname());
        target.setLastname(source.getLastname());
        target.setSpecialty(source.getSpecialty());
        target.setHireDate(source.getHireDate());
        target.setState(source.getState());
    }

    public static void updatePartially(Trainer target, Trainer source){
        if (source.getFirstname() != null) target.setFirstname(source.getFirstname());
        if (source.getLastname() != null) target.setLastname(source.getLastname());
        if (source.getSpecialty() != null) target.setSpecialty(source.getSpecialty());
        if (source.getHireDate() != null) target.setHireDate(source.getHireDate());
        if (source.getState() != null) target.setState(source.getState());
    }

    public static boolean isAllFieldsNull(Trainer trainer){
        return (trainer.getFirstname() == null &&
                trainer.getLastname() == null &&
                trainer.getSpecialty() == null &&
                trainer.getHireDate() == null &&
                trainer.getState() == null);
    }
}
