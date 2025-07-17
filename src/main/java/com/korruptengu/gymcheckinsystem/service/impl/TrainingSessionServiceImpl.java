package com.korruptengu.gymcheckinsystem.service.impl;

import com.korruptengu.gymcheckinsystem.dto.request.trainingSession.*;
import com.korruptengu.gymcheckinsystem.dto.response.TrainingSessionResponse;
import com.korruptengu.gymcheckinsystem.entity.Member;
import com.korruptengu.gymcheckinsystem.entity.Trainer;
import com.korruptengu.gymcheckinsystem.entity.TrainingSession;
import com.korruptengu.gymcheckinsystem.exception.EmptyUpdateDataException;
import com.korruptengu.gymcheckinsystem.mapper.TrainingSessionMapper;
import com.korruptengu.gymcheckinsystem.repository.TrainingSessionRepository;
import com.korruptengu.gymcheckinsystem.service.TrainingSessionService;
import com.korruptengu.gymcheckinsystem.service.fetcher.EntityFetcher;
import com.korruptengu.gymcheckinsystem.service.helper.update.TrainingSessionUpdateHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainingSessionServiceImpl implements TrainingSessionService {
    private final TrainingSessionRepository trainingSessionRepository;
    private final TrainingSessionMapper mapper;
    private final EntityFetcher fetcher;

    @Override
    public List<TrainingSessionResponse> getAllTrainingSessions(){
        List<TrainingSession> allSessions = trainingSessionRepository.findAll();
        List<TrainingSessionResponse> responseList = new ArrayList<>();
        for (TrainingSession session : allSessions){
            responseList.add(mapper.toResponse(session));
        }
        return responseList;
    }

    @Override
    public TrainingSessionResponse getTrainingSessionById(Long id){
        TrainingSession session = fetcher.fetchTrainingSession(id);
        return mapper.toResponse(session);
    }

    @Override
    public TrainingSessionResponse createTrainingSession(PostTrainingSessionRequest request){
        if (request == null) throw new IllegalArgumentException("New data must not be null");

        Member member = fetcher.fetchMember(request.memberId());
        Trainer trainer = fetcher.fetchTrainer(request.trainerId());
        TrainingSession created = mapper.postRequestToEntity(request);
        created.setTrainer(trainer);
        created.setMember(member);

        trainingSessionRepository.save(created);
        return mapper.toResponse(created);
    }

    @Override
    public TrainingSessionResponse deleteTrainingSessionById(Long id){
        TrainingSession deleted = fetcher.fetchTrainingSession(id);
        trainingSessionRepository.delete(deleted);
        return mapper.toResponse(deleted);
    }

    @Override
    public TrainingSessionResponse updateTrainingSessionCompletely(Long id, PutTrainingSessionRequest request){
        if (request == null) throw new IllegalArgumentException("Update data must not be null");

        TrainingSession existing = fetcher.fetchTrainingSession(id);

        Member member = fetcher.fetchMember(request.memberId());
        Trainer trainer = fetcher.fetchTrainer(request.trainerId());
        TrainingSession updateData = mapper.putRequestToEntity(request);
        updateData.setMember(member);
        updateData.setTrainer(trainer);

        TrainingSessionUpdateHelper.updateCompletely(existing, updateData);
        trainingSessionRepository.save(existing);

        return mapper.toResponse(existing);
    }

    @Override
    public TrainingSessionResponse updateTrainingSessionPartially(Long id, PatchTrainingSessionRequest request){
        if (request == null) throw new IllegalArgumentException("Update data must not be null");

        if (TrainingSessionUpdateHelper.isAllFieldsNull(mapper.patchRequestToEntity(request)))
            throw new EmptyUpdateDataException();

        TrainingSession existing = fetcher.fetchTrainingSession(id);

        Trainer trainer = request.trainerId() != null
                ? fetcher.fetchTrainer(request.trainerId())
                : null;
        Member member = request.memberId() != null
                ? fetcher.fetchMember(request.memberId())
                : null;

        TrainingSession updateData = mapper.patchRequestToEntity(request);
        updateData.setMember(member);
        updateData.setTrainer(trainer);

        TrainingSessionUpdateHelper.updatePartially(existing, updateData);
        trainingSessionRepository.save(existing);

        return mapper.toResponse(existing);
    }
}