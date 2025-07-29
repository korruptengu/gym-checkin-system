package com.korruptengu.gymcheckinsystem.service;

import com.korruptengu.gymcheckinsystem.dto.request.appUser.*;
import com.korruptengu.gymcheckinsystem.dto.response.AppUserResponse;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.List;

public interface AppUserService {
    List<AppUserResponse> getAllAppUsers();
    AppUserResponse getAppUserById(Long id);
    AppUserResponse getCurrentUser(Jwt jwt);
    AppUserResponse createAppUser(PostAppUserRequest request);
    AppUserResponse deleteAppUserById(Long id);
    AppUserResponse updateAppUserCompletely(Long id, PutAppUserRequest request);
    AppUserResponse updateAppUserPartially(Long id, PatchAppUserRequest request);
}
