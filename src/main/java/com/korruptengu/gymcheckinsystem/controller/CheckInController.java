package com.korruptengu.gymcheckinsystem.controller;

import com.korruptengu.gymcheckinsystem.entity.CheckIn;
import com.korruptengu.gymcheckinsystem.service.CheckInService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static com.korruptengu.gymcheckinsystem.constants.ApiPaths.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(CHECK_INS)
public class CheckInController {
    private final CheckInService checkInService;

    public CheckInController(CheckInService checkInService) {
        this.checkInService = checkInService;
    }

    @GetMapping
    public List<CheckIn> getAllCheckIns(){
        return checkInService.getAllCheckIns();
    }

    @GetMapping(ID)
    public CheckIn getCheckInById(@PathVariable Long id){
        return checkInService.getCheckInById(id);
    }

    @PostMapping
    public ResponseEntity<CheckIn> createCheckInBy(@RequestBody CheckIn checkIn){
        CheckIn createdCheckIn = checkInService.createCheckIn(checkIn);
        URI location = URI.create(CHECK_INS + "/" + createdCheckIn.getId());
        return ResponseEntity.created(location).body(createdCheckIn);
    }

    @DeleteMapping(ID)
    public ResponseEntity<CheckIn> deleteCheckIn(@PathVariable Long id){
        return ResponseEntity.ok(checkInService.deleteCheckIn(id));
    }

    @PutMapping(ID)
    public ResponseEntity<CheckIn> updateCheckIn(@PathVariable Long id, @RequestBody CheckIn updated){
        return ResponseEntity.ok(checkInService.updateCheckIn(id, updated));
    }
}
