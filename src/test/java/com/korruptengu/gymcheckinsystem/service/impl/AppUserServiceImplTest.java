package com.korruptengu.gymcheckinsystem.service.impl;

import com.korruptengu.gymcheckinsystem.dto.request.appUser.PostAppUserRequest;
import com.korruptengu.gymcheckinsystem.dto.response.AppUserResponse;
import com.korruptengu.gymcheckinsystem.entity.*;
import com.korruptengu.gymcheckinsystem.enums.UserRole;
import com.korruptengu.gymcheckinsystem.mapper.AppUserMapper;
import com.korruptengu.gymcheckinsystem.repository.AppUserRepository;
import com.korruptengu.gymcheckinsystem.service.fetcher.EntityFetcher;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AppUserServiceImplTest {

    @Mock
    private AppUserRepository repository;

    @Mock
    private AppUserMapper mapper;

    @Mock
    private EntityFetcher fetcher;

    @Mock
    private PasswordEncoder encoder;


    @InjectMocks
    private AppUserServiceImpl service;

    @Test
    void testCreateAppUser_ReturnsResponseDto() {
        // Given
        PostAppUserRequest requestDto = new PostAppUserRequest(
                "peter123",
                "PW",
                UserRole.MEMBER,
                "Peter",
                "Peterson",
                "p.peterson@mail.de",
                1L,
                null
        );
        AppUser entity = new AppUser();
        Member member = new Member();

        AppUserResponse responseDto = new AppUserResponse(
                1L,
                "peter123",
                UserRole.MEMBER,
                "Peter",
                "Peterson",
                "p.peterson@mail.de",
                1L,
                null
        );
        when(mapper.postRequestToEntity(requestDto)).thenReturn(entity);
        when(fetcher.fetchMember(1L)).thenReturn(member);
        when(repository.save(entity)).thenReturn(entity);
        when(mapper.toResponse(entity)).thenReturn(responseDto);

        // Act
        AppUserResponse created = service.createAppUser(requestDto);

        // Assert
        assertThat(responseDto.username()).isEqualTo(requestDto.username());
        assertThat(responseDto.uRole()).isEqualTo(requestDto.uRole());
        assertThat(responseDto.email()).isEqualTo(requestDto.email());
        verify(repository).save(any());
    }
}