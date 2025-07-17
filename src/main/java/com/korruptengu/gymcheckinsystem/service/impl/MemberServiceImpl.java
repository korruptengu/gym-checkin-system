package com.korruptengu.gymcheckinsystem.service.impl;

import com.korruptengu.gymcheckinsystem.dto.request.member.PatchMemberRequest;
import com.korruptengu.gymcheckinsystem.dto.request.member.PostMemberRequest;
import com.korruptengu.gymcheckinsystem.dto.request.member.PutMemberRequest;
import com.korruptengu.gymcheckinsystem.dto.response.MemberResponse;
import com.korruptengu.gymcheckinsystem.entity.Member;
import com.korruptengu.gymcheckinsystem.exception.EmptyUpdateDataException;
import com.korruptengu.gymcheckinsystem.mapper.MemberMapper;
import com.korruptengu.gymcheckinsystem.repository.MemberRepository;
import com.korruptengu.gymcheckinsystem.service.MemberService;
import com.korruptengu.gymcheckinsystem.service.fetcher.EntityFetcher;
import com.korruptengu.gymcheckinsystem.service.helper.update.MemberUpdateHelper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository repository;
    private final MemberMapper mapper;
    private final EntityFetcher fetcher;

    @Override
    public List<MemberResponse> getAllMembers(){
        List<Member> allMembers = repository.findAll();
        List<MemberResponse> responseList = new ArrayList<MemberResponse>();
        for(Member member : allMembers){
            responseList.add(mapper.toResponse(member));
        }
        return responseList;
    }

    @Override
    public MemberResponse getMemberById(Long id){
        Member member = fetcher.fetchMember(id);
        return mapper.toResponse(member);
    }

    @Override
    public MemberResponse createMember(PostMemberRequest request){
        if(request == null) throw new IllegalArgumentException("New data must not be null");
        return mapper.toResponse(repository.save(mapper.postRequestToEntity(request)));
    }

    @Override
    public MemberResponse deleteMemberById(Long id){
        Member member = fetcher.fetchMember(id);
        repository.delete(member);
        return mapper.toResponse(member);
    }

    @Override
    public MemberResponse updateMemberCompletely(Long id, PutMemberRequest request){
        if(request == null) throw new IllegalArgumentException("Update data must not be null");
        Member existing = fetcher.fetchMember(id);
        MemberUpdateHelper.updateCompletely(existing, mapper.putRequestToEntity(request));
        return mapper.toResponse(repository.save(existing));
    }

    @Override
    public MemberResponse updateMemberPartially(Long id, PatchMemberRequest request){
        if (request == null) throw new IllegalArgumentException("Update data must not be null");
        Member updateData = mapper.patchRequestToEntity(request);
        if (MemberUpdateHelper.isAllFieldsNull(updateData)) throw new EmptyUpdateDataException();
        Member existing = fetcher.fetchMember(id);
        MemberUpdateHelper.updatePartially(existing, updateData);
        return mapper.toResponse(repository.save(existing));
    }
}
