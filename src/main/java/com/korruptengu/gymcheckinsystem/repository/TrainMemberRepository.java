package com.korruptengu.gymcheckinsystem.repository;
import com.korruptengu.gymcheckinsystem.entity.TrainMember;
import com.korruptengu.gymcheckinsystem.entity.TrainMemberId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainMemberRepository extends JpaRepository<TrainMember, TrainMemberId>{

}
