package com.korruptengu.gymcheckinsystem.dto.request.trainMember;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record PutTrainMemberRequest(
        @Min(1) @NotNull Long trainerId,
        @Min(1) @NotNull Long memberId
) {}
