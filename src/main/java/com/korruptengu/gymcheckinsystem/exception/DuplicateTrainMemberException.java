package com.korruptengu.gymcheckinsystem.exception;

public class DuplicateTrainMemberException extends RuntimeException {
    public DuplicateTrainMemberException(Long trainerId, Long memberId){
        super("TrainMember with Trainer Id: " + " and " + trainerId + "Member Id: " + memberId + " already exists" );
    }
}
