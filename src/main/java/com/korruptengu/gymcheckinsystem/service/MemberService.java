package com.korruptengu.gymcheckinsystem.service;

import com.korruptengu.gymcheckinsystem.entity.Member;
import com.korruptengu.gymcheckinsystem.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<Member> getAllMembers(){
        return memberRepository.findAll();
    }

    public Member getMemberById(Long id){
        return memberRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Member with Id: " + id + " not found"));
    }

    public Member createMember(Member member){
        if (member == null) throw new IllegalArgumentException("New data must not be null");
        return memberRepository.save(member);
    }

    public Member deleteMember(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Member with ID: " + id + " not found"));
        memberRepository.delete(member);
        return member;
    }

    public Member updateMember(Long id, Member updateMember){
        if (updateMember == null) throw new IllegalArgumentException("Update data must not be null");
        Member existingMember = memberRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Member with ID: " + id + " not found"));
        if (updateMember.getFirstname() != null) existingMember.setFirstname(updateMember.getFirstname());
        if (updateMember.getLastname() != null) existingMember.setLastname(updateMember.getLastname());
        if (updateMember.getState() != null) existingMember.setState(updateMember.getState());
        return memberRepository.save(existingMember);
    }
}
