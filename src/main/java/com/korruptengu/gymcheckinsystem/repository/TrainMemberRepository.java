package com.korruptengu.gymcheckinsystem.repository;
import com.korruptengu.gymcheckinsystem.entity.TrainMember;
import com.korruptengu.gymcheckinsystem.entity.TrainMemberId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrainMemberRepository extends JpaRepository<TrainMember, TrainMemberId>{
    List<TrainMember> findByTrainerId(Long trainerId);
    List<TrainMember> findByMemberId(Long memberId);
}
