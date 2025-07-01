package com.korruptengu.gymcheckinsystem.repository;
import com.korruptengu.gymcheckinsystem.entity.CourseBooking;
import com.korruptengu.gymcheckinsystem.entity.CourseBookingId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseBookingRepository extends JpaRepository<CourseBooking, CourseBookingId>{
}
