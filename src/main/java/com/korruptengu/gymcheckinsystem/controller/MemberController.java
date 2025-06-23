package com.korruptengu.gymcheckinsystem.controller;
import static com.korruptengu.gymcheckinsystem.constants.ApiPaths.*;

import com.korruptengu.gymcheckinsystem.dto.request.member.PatchMemberRequest;
import com.korruptengu.gymcheckinsystem.dto.request.member.PostMemberRequest;
import com.korruptengu.gymcheckinsystem.dto.request.member.PutMemberRequest;
import com.korruptengu.gymcheckinsystem.dto.response.MemberResponse;
import com.korruptengu.gymcheckinsystem.entity.Member;
import com.korruptengu.gymcheckinsystem.mapper.MemberMapper;
import com.korruptengu.gymcheckinsystem.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(MEMBERS)
public class MemberController {
    private final MemberService memberService;
    private final MemberMapper mapper;

    public MemberController(MemberService memberService, MemberMapper mapper) {
        this.memberService = memberService;
        this.mapper = mapper;
    }
    
    @GetMapping
    public ResponseEntity<List<MemberResponse>> getAllMembers(){
        List<Member> allMember = memberService.getAllMembers();
        List<MemberResponse> responseList = allMember.stream()
                .map(mapper :: toResponse)
                .toList();
        return ResponseEntity
                .ok(responseList);
    }

    @GetMapping(ID)
    public ResponseEntity<MemberResponse> getMemberById(@PathVariable Long id){
        Member member = memberService.getMemberById(id);
        return ResponseEntity
                .ok(mapper.toResponse(member));
    }

    @PostMapping
    public ResponseEntity<MemberResponse> createMember(@Valid @RequestBody PostMemberRequest request){
        Member created = mapper.postRequestToEntity(request);
        created = memberService.createMember(created);
        URI location = URI.create(MEMBERS + "/" + created.getId());
        return ResponseEntity.created(location).body(mapper.toResponse(created));
    }

    @DeleteMapping(ID)
    public ResponseEntity<MemberResponse> deleteMember(@PathVariable Long id){
        Member deleted = memberService.deleteMember(id);
        return ResponseEntity
                .ok(mapper.toResponse(deleted));
    }

    @PutMapping(ID)
    public ResponseEntity<MemberResponse> updateMember(@PathVariable Long id, @Valid @RequestBody PutMemberRequest request){
        Member updated = memberService.updateMemberCompletely(id, mapper.putRequestToEntity(request));
        return ResponseEntity
                .ok(mapper.toResponse(updated));
    }

    @PatchMapping(ID)
    public ResponseEntity<MemberResponse> partiallyUpdateMember(@PathVariable Long id, @RequestBody PatchMemberRequest request){
        Member updated = memberService.updateMemberPartially(id, mapper.patchRequestToEntity(request));
        return ResponseEntity
                .ok(mapper.toResponse(updated));
    }
}
