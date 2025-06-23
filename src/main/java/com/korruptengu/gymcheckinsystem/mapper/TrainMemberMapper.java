package com.korruptengu.gymcheckinsystem.mapper;

import com.korruptengu.gymcheckinsystem.dto.response.TrainMemberResponse;
import com.korruptengu.gymcheckinsystem.entity.TrainMember;

public class TrainMemberMapper {
    public static TrainMemberResponse toResponse(TrainMember trainMember) {
        return new TrainMemberResponse(
                trainMember.getTrainer().getId(),
                trainMember.getMember().getId());
    }
}
