package com.korruptengu.gymcheckinsystem.controller;
import static com.korruptengu.gymcheckinsystem.constants.ApiPaths.*;

import com.korruptengu.gymcheckinsystem.dto.request.appUser.*;
import com.korruptengu.gymcheckinsystem.dto.response.AppUserResponse;
import com.korruptengu.gymcheckinsystem.service.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.oauth2.jwt.Jwt;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(APPUSERS)
@AllArgsConstructor
public class AppUserController {
    private final AppUserService service;

    @PreAuthorize("hasAnyRole('ADMIN', 'SERVICE')")
    @GetMapping
    public ResponseEntity<List<AppUserResponse>> getAllUsers(){
        List<AppUserResponse> allAppUsers = service.getAllAppUsers();
        return ResponseEntity
                .ok(allAppUsers);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('SERVICE') or ((hasRole('MEMBER') and #id == principal.id))")
    @GetMapping(ID)
    public ResponseEntity<AppUserResponse> getAppUserById(@PathVariable Long id) {
        AppUserResponse user = service.getAppUserById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<AppUserResponse> getCurrentUser(@AuthenticationPrincipal Jwt principal){
        AppUserResponse user = service.getCurrentUser(principal);
        return ResponseEntity.ok(user);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'SERVICE')")
    @PostMapping
    public ResponseEntity<AppUserResponse> createAppUser(@RequestBody PostAppUserRequest request) {
        AppUserResponse created = service.createAppUser(request);
        URI location = URI.create(APPUSERS + "/" + created.id());
        return ResponseEntity.created(location).body(created);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(ID)
    public ResponseEntity<AppUserResponse> deleteAppUser(@PathVariable Long id) {
        AppUserResponse deleted = service.deleteAppUserById(id);
        return ResponseEntity.ok(deleted);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping(ID)
    public ResponseEntity<AppUserResponse> updateAppUser(@PathVariable Long id, @RequestBody PutAppUserRequest request) {
        AppUserResponse updated = service.updateAppUserCompletely(id, request);
        return ResponseEntity.ok(updated);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'SERVICE')")
    @PatchMapping(ID)
    public ResponseEntity<AppUserResponse> partiallyUpdateAppUser(@PathVariable Long id, @RequestBody PatchAppUserRequest request) {
        AppUserResponse updated = service.updateAppUserPartially(id, request);
        return ResponseEntity.ok(updated);
    }
}
