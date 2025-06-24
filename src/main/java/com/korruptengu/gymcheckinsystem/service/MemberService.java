package com.korruptengu.gymcheckinsystem.service;

import com.korruptengu.gymcheckinsystem.entity.Member;
import com.korruptengu.gymcheckinsystem.exception.EmptyUpdateDataException;
import com.korruptengu.gymcheckinsystem.exception.MemberNotFoundException;
import com.korruptengu.gymcheckinsystem.repository.MemberRepository;
import com.korruptengu.gymcheckinsystem.util.update.MemberUpdateHelper;
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
                .orElseThrow(() -> new MemberNotFoundException(id));
    }

    public Member createMember(Member member){
        if (member == null) throw new IllegalArgumentException("New data must not be null");
        return memberRepository.save(member);
    }

    public Member deleteMember(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException(id));
        memberRepository.delete(member);
        return member;
    }

    public Member updateMemberCompletely(Long id, Member updateData){
        if (updateData == null) throw new IllegalArgumentException("Update data must not be null");
        Member existingMember = memberRepository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException(id));
        MemberUpdateHelper.updateCompletely(existingMember, updateData);
        return memberRepository.save(existingMember);
    }

    public Member updateMemberPartially(Long id, Member updateData){
        if (updateData == null) throw new IllegalArgumentException("Update data must not be null");
        if (MemberUpdateHelper.isAllFielddsNull(updateData)) throw new EmptyUpdateDataException();
        Member existingMember  = memberRepository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException(id));
        MemberUpdateHelper.updatePartially(existingMember, updateData);
        return memberRepository.save(existingMember);
    }
}
