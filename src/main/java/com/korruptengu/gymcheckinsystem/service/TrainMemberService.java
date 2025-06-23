package com.korruptengu.gymcheckinsystem.service;

import com.korruptengu.gymcheckinsystem.entity.Member;
import com.korruptengu.gymcheckinsystem.entity.TrainMember;
import com.korruptengu.gymcheckinsystem.entity.TrainMemberId;
import com.korruptengu.gymcheckinsystem.entity.Trainer;
import com.korruptengu.gymcheckinsystem.exception.DuplicateTrainMemberException;
import com.korruptengu.gymcheckinsystem.exception.MemberNotFoundException;
import com.korruptengu.gymcheckinsystem.exception.TrainMemberNotFoundException;
import com.korruptengu.gymcheckinsystem.exception.TrainerNotFoundException;
import com.korruptengu.gymcheckinsystem.repository.MemberRepository;
import com.korruptengu.gymcheckinsystem.repository.TrainMemberRepository;
import com.korruptengu.gymcheckinsystem.repository.TrainerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainMemberService {
    private final TrainMemberRepository trainMemberRepository;
    private final TrainerRepository trainerRepository;
    private final MemberRepository memberRepository;

    public TrainMemberService(TrainMemberRepository trainMemberRepository, TrainerRepository trainerRepository, MemberRepository memberRepository) {
        this.trainMemberRepository = trainMemberRepository;
        this.trainerRepository = trainerRepository;
        this.memberRepository = memberRepository;
    }

    public List<TrainMember> getAllTrainMembers(){
        return trainMemberRepository.findAll();
    }

    public TrainMember getTrainMemberById(Long trainerId, Long memberId){
        TrainMemberId trainMemberId = new TrainMemberId(trainerId, memberId);
        return trainMemberRepository.findById(trainMemberId)
                .orElseThrow(() -> new TrainMemberNotFoundException(trainerId, memberId));
    }

    public TrainMember createTrainMember(TrainMember trainMember){
        if (trainMember == null) throw new IllegalArgumentException("New data must not be null");
        return trainMemberRepository.save(trainMember);
    }

    public TrainMember createTrainMember(Long trainerId, Long memberId){
        if (trainerId == null || memberId == null) throw new IllegalArgumentException("Trainer Id and Member Id must not be null");

        Trainer trainer = trainerRepository.findById(trainerId)
                .orElseThrow(() -> new TrainerNotFoundException(trainerId));
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException(memberId));

        TrainMemberId trainMemberId = new TrainMemberId(trainerId, memberId);
        if (trainMemberRepository.existsById(trainMemberId)) throw new DuplicateTrainMemberException(trainerId, memberId);

        return trainMemberRepository.save(new TrainMember(trainer, member));
    }

    public TrainMember deleteTrainMember(Long trainerId, Long memberId){
        TrainMemberId trainMemberId = new TrainMemberId(trainerId, memberId);
        TrainMember deletedTrainMember = trainMemberRepository.findById(trainMemberId)
                .orElseThrow(() -> new TrainMemberNotFoundException(trainerId, memberId));
        trainMemberRepository.delete(deletedTrainMember);
        return deletedTrainMember;
    }
}