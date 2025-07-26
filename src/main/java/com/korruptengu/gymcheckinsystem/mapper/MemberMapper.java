package com.korruptengu.gymcheckinsystem.mapper;

import com.korruptengu.gymcheckinsystem.dto.request.member.PatchMemberRequest;
import com.korruptengu.gymcheckinsystem.dto.request.member.PostMemberRequest;
import com.korruptengu.gymcheckinsystem.dto.request.member.PutMemberRequest;
import com.korruptengu.gymcheckinsystem.dto.response.MemberResponse;
import com.korruptengu.gymcheckinsystem.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    @Mapping(source = "appUser.id", target = "appUserId")
    MemberResponse toResponse(Member member);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "appUser", ignore = true)
    Member putRequestToEntity(PutMemberRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "appUser", ignore = true)
    Member postRequestToEntity(PostMemberRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "appUser", ignore = true)
    Member patchRequestToEntity(PatchMemberRequest request);
}
