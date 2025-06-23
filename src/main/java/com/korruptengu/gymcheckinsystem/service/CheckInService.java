package com.korruptengu.gymcheckinsystem.service;

import com.korruptengu.gymcheckinsystem.entity.CheckIn;
import com.korruptengu.gymcheckinsystem.exception.CheckInNotFoundException;
import com.korruptengu.gymcheckinsystem.repository.CheckInRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckInService {
    private final CheckInRepository checkInRepository;

    public CheckInService(CheckInRepository checkInRepository) {
        this.checkInRepository = checkInRepository;
    }

    public List<CheckIn> getAllCheckIns(){
        return checkInRepository.findAll();
    }

    public CheckIn getCheckInById(Long id){
        return checkInRepository.findById(id)
                .orElseThrow(() -> new CheckInNotFoundException(id));
    }

    public CheckIn createCheckIn(CheckIn checkIn){
        if(checkIn == null) throw new IllegalArgumentException("New data must not be null");
        return checkInRepository.save(checkIn);
    }

    public CheckIn deleteCheckIn(Long id){
        CheckIn checkIn = checkInRepository.findById(id)
                .orElseThrow(() -> new CheckInNotFoundException(id));
        checkInRepository.delete(checkIn);
        return checkIn;
    }

    public CheckIn updateCheckIn(Long id, CheckIn updateCheckIn){
        if (updateCheckIn == null) throw new IllegalArgumentException("Update data must be not null");
        CheckIn existingCheckIn = checkInRepository.findById(id)
                .orElseThrow(() -> new CheckInNotFoundException(id));
        if (updateCheckIn.getTimestamp() != null) existingCheckIn.setTimestamp(updateCheckIn.getTimestamp());
        if (updateCheckIn.getMember() != null) existingCheckIn.setMember(updateCheckIn.getMember());
        return checkInRepository.save(existingCheckIn);
    }
}
