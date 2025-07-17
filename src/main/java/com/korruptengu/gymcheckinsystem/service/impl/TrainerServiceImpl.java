package com.korruptengu.gymcheckinsystem.service.impl;

import com.korruptengu.gymcheckinsystem.dto.request.trainer.PatchTrainerRequest;
import com.korruptengu.gymcheckinsystem.dto.request.trainer.PostTrainerRequest;
import com.korruptengu.gymcheckinsystem.dto.request.trainer.PutTrainerRequest;
import com.korruptengu.gymcheckinsystem.dto.response.TrainerResponse;
import com.korruptengu.gymcheckinsystem.entity.Trainer;
import com.korruptengu.gymcheckinsystem.exception.EmptyUpdateDataException;
import com.korruptengu.gymcheckinsystem.exception.TrainerNotFoundException;
import com.korruptengu.gymcheckinsystem.mapper.TrainerMapper;
import com.korruptengu.gymcheckinsystem.repository.TrainerRepository;
import com.korruptengu.gymcheckinsystem.service.TrainerService;
import com.korruptengu.gymcheckinsystem.service.fetcher.EntityFetcher;
import com.korruptengu.gymcheckinsystem.service.helper.update.TrainerUpdateHelper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TrainerServiceImpl implements TrainerService {
    private final TrainerRepository repository;
    private final TrainerMapper mapper;
    private final EntityFetcher fetcher;

    @Override
    public List<TrainerResponse> getAllTrainers(){
        List<Trainer> allTrainers = repository.findAll();
        List<TrainerResponse> responseList = new ArrayList<>();
        for(Trainer trainer : allTrainers){
            responseList.add(mapper.toResponse(trainer));
        }
        return responseList;
    }

    @Override
    public TrainerResponse getTrainerById(Long id){
        return mapper.toResponse(fetcher.fetchTrainer(id));
    }

    @Override
    public TrainerResponse createTrainer(PostTrainerRequest request){
        if (request == null) throw new IllegalArgumentException("New data must not be null");
        return mapper.toResponse(repository.save(mapper.postRequestToEntity(request)));
    }

    @Override
    public TrainerResponse deleteTrainerById(Long id){
        Trainer deleted = fetcher.fetchTrainer(id);
        repository.delete(deleted);
        return mapper.toResponse(deleted);
    }

    @Override
    public TrainerResponse updateTrainerCompletely(Long id, PutTrainerRequest request){
        if (request == null) throw new IllegalArgumentException("Update data must not be null");
        Trainer existing = fetcher.fetchTrainer(id);
        TrainerUpdateHelper.updateCompletely(existing, mapper.putRequestToEntity(request));
        return mapper.toResponse(repository.save(existing));
    }

    @Override
    public TrainerResponse updateTrainerPartially(Long id, PatchTrainerRequest request){
        if (request == null) throw new IllegalArgumentException("Update data must not be null");
        Trainer updateData = mapper.patchRequestToEntity(request);
        if (TrainerUpdateHelper.isAllFieldsNull(updateData)) throw new EmptyUpdateDataException();
        Trainer existing = fetcher.fetchTrainer(id);
        TrainerUpdateHelper.updatePartially(existing, updateData);
        return mapper.toResponse(repository.save(existing));
    }


}
