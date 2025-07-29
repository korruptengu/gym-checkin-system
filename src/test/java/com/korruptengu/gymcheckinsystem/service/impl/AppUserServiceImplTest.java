package com.korruptengu.gymcheckinsystem.service.impl;

import com.korruptengu.gymcheckinsystem.dto.request.appUser.PostAppUserRequest;
import com.korruptengu.gymcheckinsystem.dto.response.AppUserResponse;
import com.korruptengu.gymcheckinsystem.entity.AppUser;
import com.korruptengu.gymcheckinsystem.entity.Member;
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
import static org.junit.jupiter.api.Assertions.*;
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
    void createAppUser_shouldMapAndPersistUserCorrectly() {
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
                "MEMBER",
                "Peter",
                "Peterson",
                "p.peterson@mail.de",
                1L,
                null
        );

        when(mapper.postRequestToEntity(requestDto)).thenReturn(entity);
        when(fetcher.fetchMemberById(1L)).thenReturn(member);
        when(repository.save(entity)).thenReturn(entity);
        when(mapper.toResponse(entity)).thenReturn(responseDto);

        // Act
        AppUserResponse created = service.createAppUser(requestDto);

        // Assert
        assertThat(created.username()).isEqualTo("peter123");
        assertThat(created.role()).isEqualTo("MEMBER");
        assertThat(created.email()).isEqualTo("p.peterson@mail.de");
        verify(repository).save(entity);
    }

    @Test
    void getAppUserById_shouldReturnMappedResponse() {
        AppUser user = new AppUser();
        AppUserResponse response = new AppUserResponse(
                1L,
                "user",
                "MEMBER",
                "Max",
                "Mustermann",
                "mustermann@mail.com",
                2L,
                null);

        when(fetcher.fetchAppUserById(1L)).thenReturn(user);
        when(mapper.toResponse(user)).thenReturn(response);

        AppUserResponse result = service.getAppUserById(1L);

        assertNotNull(result);
        assertEquals(1L, result.id());
        assertEquals("user", result.username());
        assertEquals("MEMBER", result.role());
        verify(fetcher).fetchAppUserById(1L);
        verify(mapper).toResponse(user);
    }

    @Test
    void createAppUser_shouldSaveUserWithEncodedPasswordAndMember() {
        PostAppUserRequest request = new PostAppUserRequest(
                "username",
                "pass",
                UserRole.MEMBER,
                "Max",
                "Mustermann",
                "mustermann@mail.com",
                2L,
                null
        );
        Member member = new Member();
        AppUser user = new AppUser(
                1L,
                "username",
                "pass",
                UserRole.MEMBER,
                "Max",
                "Mustermann",
                "mustermann@mail.com",
                member,
                null

        );
        AppUserResponse response = new AppUserResponse(
                1L,
                "username",
                "MEMBER",
                "Max",
                "Mustermann",
                "mustermann@mail.com",
                2L,
                null);

        when(mapper.postRequestToEntity(request)).thenReturn(user);
        when(encoder.encode("pass")).thenReturn("encoded-pass");
        when(fetcher.fetchMemberById(2L)).thenReturn(member);
        when(repository.save(user)).thenReturn(user);
        when(mapper.toResponse(user)).thenReturn(response);

        AppUserResponse result = service.createAppUser(request);

        assertNotNull(result);
        assertEquals("username", result.username());
        assertEquals("MEMBER", result.role());
        assertEquals("encoded-pass", user.getPassword());
        assertEquals(member, user.getMember());
        assertNull(user.getTrainer());
        verify(mapper).postRequestToEntity(request);
        verify(encoder).encode("pass");
        verify(fetcher).fetchMemberById(2L);
        verify(repository).save(user);
    }
}