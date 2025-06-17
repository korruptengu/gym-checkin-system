package com.korruptengu.gymcheckinsystem.controller;

import com.korruptengu.gymcheckinsystem.entity.Member;
import com.korruptengu.gymcheckinsystem.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    
    @GetMapping
    public List<Member> getAllMembers(){
        return memberService.getAllMembers();
    }

    @PostMapping
    public ResponseEntity<Member> createMember(@RequestBody Member member){
        return ResponseEntity.ok(memberService.createMember(member));

    }

    @GetMapping("/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable Long id){
        Member member = memberService.getMemberbyId(id);
        if (member != null) return ResponseEntity.ok(member);
        else return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id){
        boolean deleted = memberService.deleteMember(id);
        if (deleted) return ResponseEntity.noContent().build();
        else return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable Long id, @RequestBody Member updated){
        Member updatedMember = memberService.updateMember(id, updated);
        if (updatedMember != null) return ResponseEntity.ok(updatedMember);
        else return ResponseEntity.notFound().build();
    }
}
