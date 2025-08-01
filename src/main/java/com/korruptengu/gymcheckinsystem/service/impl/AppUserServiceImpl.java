package com.korruptengu.gymcheckinsystem.service.impl;

import com.korruptengu.gymcheckinsystem.dto.request.appUser.PatchAppUserRequest;
import com.korruptengu.gymcheckinsystem.dto.request.appUser.PostAppUserRequest;
import com.korruptengu.gymcheckinsystem.dto.request.appUser.PutAppUserRequest;
import com.korruptengu.gymcheckinsystem.dto.response.AppUserResponse;
import com.korruptengu.gymcheckinsystem.entity.AppUser;
import com.korruptengu.gymcheckinsystem.exception.EmptyUpdateDataException;
import com.korruptengu.gymcheckinsystem.exception.RoleConflictException;
import com.korruptengu.gymcheckinsystem.mapper.AppUserMapper;
import com.korruptengu.gymcheckinsystem.repository.AppUserRepository;
import com.korruptengu.gymcheckinsystem.service.AppUserService;
import com.korruptengu.gymcheckinsystem.service.fetcher.EntityFetcher;
import com.korruptengu.gymcheckinsystem.service.helper.update.AppUserUpdateHelper;
import com.korruptengu.gymcheckinsystem.utils.RequestValidator;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class AppUserServiceImpl implements AppUserService {
    private final AppUserRepository repository;
    private final AppUserMapper mapper;
    private final EntityFetcher fetcher;
    private final PasswordEncoder encoder;

    @Override
    public List<AppUserResponse> getAllAppUsers(){
        return repository.findAll()
                .stream()
                .map(mapper :: toResponse)
                .toList();
    }

    @Override
    public AppUserResponse getAppUserById(Long id){
        return mapper.toResponse(fetcher.fetchAppUserById(id));
    }

    @Override
    public AppUserResponse getCurrentUser(Jwt jwt){
        String username = jwt.getSubject();

        AppUser user = fetcher.fetchAppUserByUsername(username);

        return mapper.toResponse(user);
    }

    @Override
    public AppUserResponse createAppUser(PostAppUserRequest request){
        RequestValidator.requireNonNull(request, "post", "appUser");

        AppUser created = mapper.postRequestToEntity(request);
        created.setPassword(encoder.encode(request.password()));

        if (request.memberId() != null && request.trainerId() != null) {
            throw new RoleConflictException("AppUser darf nur eine Rolle haben: MEMBER oder TRAINER.");
        }

        if (request.trainerId() != null) created.setTrainer(fetcher.fetchTrainerById(request.trainerId()));
        if (request.memberId() != null) created.setMember(fetcher.fetchMemberById(request.memberId()));

        AppUser saved = repository.save(created);
        log.info("Neuer AppUser erstellt mit [id={}, username={}, role={}]", saved.getId(), saved.getUsername(), saved.getURole());

        return mapper.toResponse(saved);
    }

    @Override
    public AppUserResponse deleteAppUserById(Long id){
        AppUser deleted = fetcher.fetchAppUserById(id);

        if (deleted.getMember() != null) deleted.getMember().setAppUser(null);
        if (deleted.getTrainer() != null) deleted.getTrainer().setAppUser(null);

        repository.delete(deleted);
        return mapper.toResponse(deleted);
    }

    @Override
    public AppUserResponse updateAppUserCompletely(Long id, PutAppUserRequest request){
        RequestValidator.requireNonNull(request, "put", "appUser");

        AppUser updateData = mapper.putRequestToEntity(request);
        updateData.setPassword(encoder.encode(request.password()));
        if (request.trainerId() != null) updateData.setTrainer(fetcher.fetchTrainerById(request.trainerId()));
        if (request.memberId() != null) updateData.setMember(fetcher.fetchMemberById(request.memberId()));

        AppUser existing = fetcher.fetchAppUserById(id);
        AppUserUpdateHelper.updateCompletely(existing, updateData);

        return mapper.toResponse(repository.save(existing));
    }

    @Override
    public AppUserResponse updateAppUserPartially(Long id, PatchAppUserRequest request){
        RequestValidator.requireNonNull(request, "patch", "appUser");

        AppUser updateData = mapper.patchRequestToEntity(request);
        updateData.setPassword(encoder.encode(request.password()));
        if (request.trainerId() != null) updateData.setTrainer(fetcher.fetchTrainerById(request.trainerId()));
        if (request.memberId() != null) updateData.setMember(fetcher.fetchMemberById(request.memberId()));

        if (AppUserUpdateHelper.isAllFieldsNull(updateData)) {
            log.warn("PATCH abgebrochen: Keine Felder gesetzt für AppUser mit ID={}", id);
            throw new EmptyUpdateDataException();
        }

        AppUser existing = fetcher.fetchAppUserById(id);
        AppUserUpdateHelper.updatePartially(existing, updateData);

        return mapper.toResponse(repository.save(existing));
    }
}
