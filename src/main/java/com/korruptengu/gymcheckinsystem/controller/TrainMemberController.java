package com.korruptengu.gymcheckinsystem.controller;
import static com.korruptengu.gymcheckinsystem.constants.ApiPaths.*;

import com.korruptengu.gymcheckinsystem.entity.TrainMember;
import com.korruptengu.gymcheckinsystem.service.TrainMemberService;
import com.korruptengu.gymcheckinsystem.dto.request.trainMember.PutTrainMemberRequest;
import com.korruptengu.gymcheckinsystem.dto.response.TrainMemberResponse;
import com.korruptengu.gymcheckinsystem.mapper.TrainMemberMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(TRAIN_MEMBERS)
public class TrainMemberController {
    private final TrainMemberService trainMemberService;

    public TrainMemberController(TrainMemberService trainMemberService) {
        this.trainMemberService = trainMemberService;
    }

    @GetMapping
    public ResponseEntity<List<TrainMemberResponse>> getAllTrainMembers(){
        List<TrainMember> allTrainMember = trainMemberService.getAllTrainMembers();

        List<TrainMemberResponse> responseList = allTrainMember.stream()
                .map(TrainMemberMapper ::toResponse)
                .toList();

        return ResponseEntity.ok(responseList);
    }

    @GetMapping(TRAIN_MEMBER_ID)
    public ResponseEntity<TrainMemberResponse> getTrainMemberById(@PathVariable Long trainerId, @PathVariable Long memberId){
        TrainMember trainMember = trainMemberService.getTrainMemberById(trainerId, memberId);
        return ResponseEntity
                .ok(TrainMemberMapper.toResponse(trainMember));
    }

    @PostMapping
    public ResponseEntity<TrainMemberResponse> createTrainMember(@RequestBody PutTrainMemberRequest request){
        TrainMember createdTrainMember = trainMemberService.createTrainMember(request.trainerId(), request.memberId());
        URI location = URI.create(TRAIN_MEMBERS + "/"
                + createdTrainMember.getTrainer().getId() + "/"
                + createdTrainMember.getMember().getId());
        return ResponseEntity
                .created(location)
                .body(TrainMemberMapper.toResponse(createdTrainMember));
    }

    @DeleteMapping(TRAIN_MEMBER_ID)
    public ResponseEntity<TrainMemberResponse> deleteTrainMember(@PathVariable Long trainerId, @PathVariable Long memberId){
        TrainMember deletedTrainMember = trainMemberService.deleteTrainMember(trainerId, memberId);
        return ResponseEntity
                .ok(TrainMemberMapper.toResponse(deletedTrainMember));

    }
}
