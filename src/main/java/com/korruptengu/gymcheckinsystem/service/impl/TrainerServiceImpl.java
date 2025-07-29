package com.korruptengu.gymcheckinsystem.service.impl;

import com.korruptengu.gymcheckinsystem.dto.request.trainer.*;
import com.korruptengu.gymcheckinsystem.dto.response.TrainerResponse;
import com.korruptengu.gymcheckinsystem.entity.AppUser;
import com.korruptengu.gymcheckinsystem.entity.Trainer;
import com.korruptengu.gymcheckinsystem.exception.EmptyUpdateDataException;
import com.korruptengu.gymcheckinsystem.exception.RoleConflictException;
import com.korruptengu.gymcheckinsystem.mapper.TrainerMapper;
import com.korruptengu.gymcheckinsystem.repository.TrainerRepository;
import com.korruptengu.gymcheckinsystem.service.TrainerService;
import com.korruptengu.gymcheckinsystem.service.fetcher.EntityFetcher;
import com.korruptengu.gymcheckinsystem.service.helper.update.TrainerUpdateHelper;
import com.korruptengu.gymcheckinsystem.utils.RequestValidator;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
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
        return mapper.toResponse(fetcher.fetchTrainerById(id));
    }

    @Override
    public TrainerResponse createTrainer(PostTrainerRequest request){
        RequestValidator.requireNonNull(request, "post", "trainer");

        AppUser appUser = fetcher.fetchAppUserById(request.appUserId());
        if (appUser.getMember() != null) throw new RoleConflictException("Der AppUser ist bereits ein Member und kann nicht gleichzeitig ein Trainer sein.");
        if (appUser.getTrainer() != null) throw new RoleConflictException("Dieser AppUser ist bereits Trainer.");

        Trainer trainer = mapper.postRequestToEntity(request);
        trainer.setAppUser(appUser);

        Trainer created = repository.save(trainer);
        appUser.setTrainer(created);  // bidirektionale Konsistenz

        return mapper.toResponse(created);
    }

    @Override
    public TrainerResponse deleteTrainerById(Long id){
        Trainer deleted = fetcher.fetchTrainerById(id);
        AppUser user = deleted.getAppUser();
        user.setTrainer(null);
        deleted.setAppUser(null);

        repository.delete(deleted);
        return mapper.toResponse(deleted);
    }

    @Override
    public TrainerResponse updateTrainerCompletely(Long id, PutTrainerRequest request){
        RequestValidator.requireNonNull(request, "put", "trainer");
        Trainer existing = fetcher.fetchTrainerById(id);

        TrainerUpdateHelper.updateCompletely(existing, mapper.putRequestToEntity(request));

        return mapper.toResponse(repository.save(existing));
    }

    @Override
    public TrainerResponse updateTrainerPartially(Long id, PatchTrainerRequest request){
        RequestValidator.requireNonNull(request, "patch", "trainer");
        Trainer updateData = mapper.patchRequestToEntity(request);

        if (TrainerUpdateHelper.isAllFieldsNull(updateData)) throw new EmptyUpdateDataException();
        Trainer existing = fetcher.fetchTrainerById(id);
        TrainerUpdateHelper.updatePartially(existing, updateData);

        return mapper.toResponse(repository.save(existing));
    }


}
