package com.korruptengu.gymcheckinsystem.service.impl;

import com.korruptengu.gymcheckinsystem.dto.request.courseSession.*;
import com.korruptengu.gymcheckinsystem.dto.response.CourseSessionResponse;
import com.korruptengu.gymcheckinsystem.entity.CourseSession;
import com.korruptengu.gymcheckinsystem.entity.CourseType;
import com.korruptengu.gymcheckinsystem.entity.Trainer;
import com.korruptengu.gymcheckinsystem.exception.*;
import com.korruptengu.gymcheckinsystem.mapper.CourseSessionMapper;
import com.korruptengu.gymcheckinsystem.repository.CourseSessionRepository;
import com.korruptengu.gymcheckinsystem.service.CourseSessionService;
import com.korruptengu.gymcheckinsystem.service.fetcher.EntityFetcher;
import com.korruptengu.gymcheckinsystem.service.helper.update.CourseSessionUpdateHelper;
import com.korruptengu.gymcheckinsystem.service.helper.validator.TrainerAvailabilityValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseSessionServiceImpl implements CourseSessionService {
    private final CourseSessionRepository repository;
    private final CourseSessionMapper mapper;
    private final EntityFetcher fetcher;
    private final TrainerAvailabilityValidator validator;

    @Override
    public List<CourseSessionResponse> getAllCourseSessions() {
        List<CourseSession> allSessions = repository.findAll();
        List<CourseSessionResponse> responseList = new ArrayList<>();
        for (CourseSession session : allSessions) {
            responseList.add(mapper.toResponse(session));
        }
        return responseList;
    }

    @Override
    public CourseSessionResponse getCourseSessionById(Long id) {
        CourseSession session = fetcher.fetchCourseSession(id);
        return mapper.toResponse(session);
    }

    @Override
    public CourseSessionResponse createCourseSession(PostCourseSessionRequest request) {
        if (request == null) throw new IllegalArgumentException("New data must not be null");

        CourseSession created = mapper.postRequestToEntity(request);
        created.setCourseType(fetcher.fetchCourseType(request.courseTypeId()));
        created.setTrainer(fetcher.fetchTrainer(request.trainerId()));

        if(validator.isTrainerAlreadyBooked(created)) {
            throw new TrainerAlreadyBookedException(
                    created.getTrainer().getId(),
                    created.getStartTime(),
                    created.getEndTime());
        }
        repository.save(created);
        return mapper.toResponse(created);
    }

    @Override
    public CourseSessionResponse deleteCourseSessionById(Long id) {
        CourseSession session = fetcher.fetchCourseSession(id);
        repository.delete(session);
        return mapper.toResponse(session);
    }

    @Override
    public CourseSessionResponse updateCourseSessionCompletely(Long id, PutCourseSessionRequest request) {
        if (request == null) throw new IllegalArgumentException("Update data must not be null");

        CourseSession existing = fetcher.fetchCourseSession(id);

        CourseType courseType = fetcher.fetchCourseType(request.courseTypeId());
        Trainer trainer = fetcher.fetchTrainer(request.trainerId());

        CourseSession updateData = mapper.putRequestToEntity(request);
        updateData.setCourseType(courseType);
        updateData.setTrainer(trainer);

        if(validator.isTrainerAlreadyBookedExceptSelf(updateData, id)) {
            throw new TrainerAlreadyBookedException(
                    trainer.getId(),
                    updateData.getStartTime(),
                    updateData.getEndTime());
        }

        CourseSessionUpdateHelper.updateCompletely(existing, updateData);
        repository.save(existing);

        return mapper.toResponse(existing);
    }

    @Override
    public CourseSessionResponse updateCourseSessionPartially(Long id, PatchCourseSessionRequest request) {
        if (request == null) throw new IllegalArgumentException("Update data must not be null");

        CourseType courseType = request.courseTypeId() != null
                ? fetcher.fetchCourseType(request.courseTypeId())
                : null;

        Trainer trainer = request.trainerId() != null
                ? fetcher.fetchTrainer(request.trainerId())
                : null;

        CourseSession partialUpdate = mapper.patchRequestToEntity(request);
        partialUpdate.setCourseType(courseType);
        partialUpdate.setTrainer(trainer);
        if (CourseSessionUpdateHelper.isAllFieldsNull(partialUpdate))
            throw new EmptyUpdateDataException();

        CourseSession existing = fetcher.fetchCourseSession(id);
        CourseSession merged = CourseSessionUpdateHelper.buildMergeCourseSession(existing, partialUpdate);

        if(validator.isTrainerAlreadyBookedExceptSelf(merged, id)) {
            throw new TrainerAlreadyBookedException(
                    merged.getTrainer().getId(),
                    merged.getStartTime(),
                    merged.getEndTime());
        }

        CourseSessionUpdateHelper.updatePartially(existing, merged);
        repository.save(existing);

        return mapper.toResponse(existing);
    }
}
