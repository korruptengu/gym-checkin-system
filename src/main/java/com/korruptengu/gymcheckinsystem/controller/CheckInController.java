package com.korruptengu.gymcheckinsystem.controller;

import com.korruptengu.gymcheckinsystem.dto.request.checkIn.*;
import com.korruptengu.gymcheckinsystem.dto.response.CheckInResponse;
import com.korruptengu.gymcheckinsystem.service.CheckInService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static com.korruptengu.gymcheckinsystem.constants.ApiPaths.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(CHECK_INS)
@AllArgsConstructor
public class CheckInController {
    private final CheckInService service;

    @GetMapping
    public ResponseEntity<List<CheckInResponse>> getAllCheckIns(){
        List<CheckInResponse> allCheckIns = service.getAllCheckIns();
        return ResponseEntity.ok(allCheckIns);
    }

    @GetMapping(ID)
    public ResponseEntity<CheckInResponse> getCheckInById(@PathVariable Long id){
        CheckInResponse checkIn = service.getCheckInById(id);
        return ResponseEntity.ok(checkIn);
    }

    @PostMapping
    public ResponseEntity<CheckInResponse> createCheckInBy(@Valid @RequestBody PostCheckInRequest request){
        CheckInResponse createdCheckIn = service.createCheckIn(request);
        URI location = URI.create(CHECK_INS + "/" + createdCheckIn.id());
        return ResponseEntity.created(location).body(createdCheckIn);
    }

    @DeleteMapping(ID)
    public ResponseEntity<CheckInResponse> deleteCheckIn(@PathVariable Long id){
        CheckInResponse deleted = service.deleteCheckInById(id);
        return ResponseEntity.ok(deleted);
    }

    @PutMapping(ID)
    public ResponseEntity<CheckInResponse> updateCheckIn(@PathVariable Long id, @Valid @RequestBody PutCheckInRequest request){
        CheckInResponse updated = service.updateCheckInCompletely(id, request);
        return ResponseEntity.ok(updated);
    }

    @PatchMapping(ID)
    public ResponseEntity<CheckInResponse> partiallyUpdateMember(@PathVariable Long id, @RequestBody PatchCheckInRequest request){
        CheckInResponse updated = service.updateCheckInPartially(id, request);
        return ResponseEntity.ok(updated);
    }
}
