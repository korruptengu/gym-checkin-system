package com.korruptengu.gymcheckinsystem.repository;
import com.korruptengu.gymcheckinsystem.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long>{
}
