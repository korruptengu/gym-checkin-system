package com.korruptengu.gymcheckinsystem.service;

import com.korruptengu.gymcheckinsystem.entity.Member;
import com.korruptengu.gymcheckinsystem.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<Member> getAllMembers(){
        return memberRepository.findAll();
    }

    public Member getMemberbyId(Long id){
        return memberRepository.findById(id).orElse(null);
    }

    public Member createMember(Member member){
        return memberRepository.save(member);
    }

    public boolean deleteMember(Long id){
        Optional<Member> memberOptional = memberRepository.findById(id);
        if (memberOptional.isPresent()) {
            memberRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public Member updateMember(Long id, Member updateMember){
        Member existingMember = memberRepository.findById(id).orElse(null);
        if (existingMember == null){
            return null;
        }
        if (updateMember.getFirstname() != null) existingMember.setFirstname(updateMember.getFirstname());
        if (updateMember.getLastname() != null) existingMember.setLastname(updateMember.getLastname());
        if (updateMember.getState() != null) existingMember.setState(updateMember.getState());
        return memberRepository.save(existingMember);
    }
}
