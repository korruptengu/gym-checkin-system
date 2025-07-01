package com.korruptengu.gymcheckinsystem.controller;
import static com.korruptengu.gymcheckinsystem.constants.ApiPaths.*;

import com.korruptengu.gymcheckinsystem.dto.request.member.PatchMemberRequest;
import com.korruptengu.gymcheckinsystem.dto.request.member.PostMemberRequest;
import com.korruptengu.gymcheckinsystem.dto.request.member.PutMemberRequest;
import com.korruptengu.gymcheckinsystem.dto.response.MemberResponse;
import com.korruptengu.gymcheckinsystem.service.MemberService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(MEMBERS)
@AllArgsConstructor
public class MemberController {
    private final MemberService service;
    
    @GetMapping
    public ResponseEntity<List<MemberResponse>> getAllMembers(){
        List<MemberResponse> allMembers = service.getAllMembers();
        return ResponseEntity
                .ok(allMembers);
    }

    @GetMapping(ID)
    public ResponseEntity<MemberResponse> getMemberById(@PathVariable Long id){
        MemberResponse member = service.getMemberById(id);
        return ResponseEntity
                .ok(member);
    }

    @PostMapping
    public ResponseEntity<MemberResponse> createMember(@Valid @RequestBody PostMemberRequest request){
        MemberResponse created = service.createMember(request);
        URI location = URI.create(MEMBERS + "/" + created.id());
        return ResponseEntity.created(location).body(created);
    }

    @DeleteMapping(ID)
    public ResponseEntity<MemberResponse> deleteMember(@PathVariable Long id){
        MemberResponse deleted = service.deleteMemberById(id);
        return ResponseEntity
                .ok(deleted);
    }

    @PutMapping(ID)
    public ResponseEntity<MemberResponse> updateMember(@PathVariable Long id, @Valid @RequestBody PutMemberRequest request){
        MemberResponse updated = service.updateMemberCompletely(id, request);
        return ResponseEntity
                .ok(updated);
    }

    @PatchMapping(ID)
    public ResponseEntity<MemberResponse> partiallyUpdateMember(@PathVariable Long id, @RequestBody PatchMemberRequest request){
        MemberResponse updated = service.updateMemberPartially(id, request);
        return ResponseEntity
                .ok(updated);
    }
}
