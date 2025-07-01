package com.korruptengu.gymcheckinsystem.service;

import com.korruptengu.gymcheckinsystem.dto.request.member.PatchMemberRequest;
import com.korruptengu.gymcheckinsystem.dto.request.member.PostMemberRequest;
import com.korruptengu.gymcheckinsystem.dto.request.member.PutMemberRequest;
import com.korruptengu.gymcheckinsystem.dto.response.MemberResponse;
import com.korruptengu.gymcheckinsystem.entity.Member;

import java.util.List;

public interface MemberService {
    List<MemberResponse> getAllMembers();
    MemberResponse getMemberById(Long id);
    MemberResponse createMember(PostMemberRequest request);
    MemberResponse deleteMemberById(Long id);
    MemberResponse updateMemberCompletely(Long id, PutMemberRequest request);
    MemberResponse updateMemberPartially(Long id, PatchMemberRequest request);
}
