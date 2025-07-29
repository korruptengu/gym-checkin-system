package com.korruptengu.gymcheckinsystem.service.impl;

import com.korruptengu.gymcheckinsystem.dto.request.member.*;
import com.korruptengu.gymcheckinsystem.dto.response.MemberResponse;
import com.korruptengu.gymcheckinsystem.entity.AppUser;
import com.korruptengu.gymcheckinsystem.entity.Member;
import com.korruptengu.gymcheckinsystem.exception.EmptyUpdateDataException;
import com.korruptengu.gymcheckinsystem.exception.RoleConflictException;
import com.korruptengu.gymcheckinsystem.mapper.MemberMapper;
import com.korruptengu.gymcheckinsystem.repository.MemberRepository;
import com.korruptengu.gymcheckinsystem.service.MemberService;
import com.korruptengu.gymcheckinsystem.service.fetcher.EntityFetcher;
import com.korruptengu.gymcheckinsystem.service.helper.update.MemberUpdateHelper;
import com.korruptengu.gymcheckinsystem.utils.RequestValidator;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository repository;
    private final MemberMapper mapper;
    private final EntityFetcher fetcher;

    @Override
    public List<MemberResponse> getAllMembers(){
        List<Member> allMembers = repository.findAll();
        List<MemberResponse> responseList = new ArrayList<>();
        for(Member member : allMembers){
            responseList.add(mapper.toResponse(member));
        }
        return responseList;
    }

    @Override
    public MemberResponse getMemberById(Long id){
        Member member = fetcher.fetchMemberById(id);
        return mapper.toResponse(member);
    }

    @Override
    public MemberResponse createMember(PostMemberRequest request){
        RequestValidator.requireNonNull(request, "post", "member");

        AppUser appUser = fetcher.fetchAppUserById(request.appUserId());
        if (appUser.getTrainer() != null) throw new RoleConflictException("Der AppUser ist bereits ein Trainer und kann nicht gleichzeitig ein Member sein.");
        if (appUser.getMember() != null) throw new RoleConflictException("Dieser AppUser ist bereits Member.");

        Member member = mapper.postRequestToEntity(request);
        member.setAppUser(appUser);

        Member created = repository.save(member);
        appUser.setMember(created); // bidirektionale Konsistenz

        return mapper.toResponse(created);
    }

    @Override
    public MemberResponse deleteMemberById(Long id){
        Member deleted = fetcher.fetchMemberById(id);
        AppUser user = deleted.getAppUser();
        user.setMember(null);
        deleted.setAppUser(null);

        repository.delete(deleted);
        return mapper.toResponse(deleted);
    }

    @Override
    public MemberResponse updateMemberCompletely(Long id, PutMemberRequest request){
        RequestValidator.requireNonNull(request, "put", "member");
        Member existing = fetcher.fetchMemberById(id);

        MemberUpdateHelper.updateCompletely(existing, mapper.putRequestToEntity(request));

        return mapper.toResponse(repository.save(existing));
    }

    @Override
    public MemberResponse updateMemberPartially(Long id, PatchMemberRequest request){
        RequestValidator.requireNonNull(request, "patch", "member");
        Member updateData = mapper.patchRequestToEntity(request);

        if (MemberUpdateHelper.isAllFieldsNull(updateData)) throw new EmptyUpdateDataException();
        Member existing = fetcher.fetchMemberById(id);
        MemberUpdateHelper.updatePartially(existing, updateData);

        return mapper.toResponse(repository.save(existing));
    }
}
