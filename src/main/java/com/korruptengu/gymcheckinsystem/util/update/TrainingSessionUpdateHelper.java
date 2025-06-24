package com.korruptengu.gymcheckinsystem.util.update;

import com.korruptengu.gymcheckinsystem.entity.TrainingSession;

public class TrainingSessionUpdateHelper {
    public static void updateCompletely(TrainingSession target, TrainingSession source){
        target.setStartTime(source.getStartTime());
        target.setDuration(source.getDuration());
        target.setTrainer(source.getTrainer());
        target.setMember(source.getMember());
    }

    public static void updatePartially(TrainingSession target, TrainingSession source){
        if (source.getStartTime() != null) target.setStartTime(source.getStartTime());
        if (source.getDuration() != null) target.setDuration(source.getDuration());
        if (source.getTrainer() != null) target.setTrainer(source.getTrainer());
        if (source.getMember() != null) target.setMember(source.getMember());
    }

    public static boolean isAllFieldsNull(TrainingSession trainingSession){
        return (trainingSession.getStartTime() == null &&
                trainingSession.getDuration() == null &&
                trainingSession.getTrainer() == null &&
                trainingSession.getMember() == null);
    }
}
