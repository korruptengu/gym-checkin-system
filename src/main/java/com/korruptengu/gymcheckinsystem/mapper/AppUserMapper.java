package com.korruptengu.gymcheckinsystem.mapper;

import com.korruptengu.gymcheckinsystem.dto.request.appUser.*;
import com.korruptengu.gymcheckinsystem.dto.response.AppUserResponse;
import com.korruptengu.gymcheckinsystem.entity.AppUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface AppUserMapper {

    @Mapping(source = "member.id", target = "memberId")
    @Mapping(source = "trainer.id", target = "trainerId")
    AppUserResponse toResponse(AppUser appUser);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "member", ignore = true)
    @Mapping(target = "trainer", ignore = true)
    public AppUser postRequestToEntity(PostAppUserRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "member", ignore = true)
    @Mapping(target = "trainer", ignore = true)
    public AppUser putRequestToEntity(PutAppUserRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "member", ignore = true)
    @Mapping(target = "trainer", ignore = true)
    public AppUser patchRequestToEntity(PatchAppUserRequest request);
}