package com.korruptengu.gymcheckinsystem.repository;

import com.korruptengu.gymcheckinsystem.entity.CheckIn;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CheckInRepository extends JpaRepository<CheckIn, Long> {

}
