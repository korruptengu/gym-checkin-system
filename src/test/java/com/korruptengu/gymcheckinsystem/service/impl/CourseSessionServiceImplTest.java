package com.korruptengu.gymcheckinsystem.service.impl;

import com.korruptengu.gymcheckinsystem.dto.request.courseSession.PostCourseSessionRequest;
import com.korruptengu.gymcheckinsystem.dto.response.CourseSessionResponse;
import com.korruptengu.gymcheckinsystem.entity.CourseSession;
import com.korruptengu.gymcheckinsystem.entity.CourseType;
import com.korruptengu.gymcheckinsystem.entity.Trainer;
import com.korruptengu.gymcheckinsystem.mapper.CourseSessionMapper;
import com.korruptengu.gymcheckinsystem.repository.CourseSessionRepository;
import com.korruptengu.gymcheckinsystem.service.fetcher.EntityFetcher;
import com.korruptengu.gymcheckinsystem.service.helper.validator.TrainerAvailabilityValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CourseSessionServiceImplTest {

    @Mock
    private CourseSessionRepository repository;

    @Mock
    private CourseSessionMapper mapper;

    @Mock
    private EntityFetcher fetcher;

    @Mock
    private TrainerAvailabilityValidator validator;

    @InjectMocks
    private CourseSessionServiceImpl service;

    @Test
    void testGetAllCourseSessions_ReturnsResponseDtoList() {
        // Given
        CourseSession entity = new CourseSession();
        CourseSessionResponse responseDto = new CourseSessionResponse(
                1L, LocalDateTime.now(), 45, 1L, 1L);

        when(repository.findAll()).thenReturn(List.of(entity));
        when(mapper.toResponse(entity)).thenReturn(responseDto);

        // When
        List<CourseSessionResponse> result = service.getAllCourseSessions();

        // Then
        assertEquals(1, result.size());
        assertEquals(responseDto, result.get(0));
        verify(repository).findAll();
        verify(mapper).toResponse(entity);
    }

    @Test
    void testGetCourseSessionById_ReturnsResponseDto(){
        // Given
        Long courseSessionId = 1L;
        CourseSession entity = new CourseSession();
        CourseSessionResponse responseDto = new CourseSessionResponse(
                courseSessionId,
                LocalDateTime.now(),
                45,
                1L,
                1L);

        when(fetcher.fetchCourseSessionById(courseSessionId)).thenReturn(entity);
        when(mapper.toResponse(entity)).thenReturn(responseDto);

        // when
        CourseSessionResponse result = service.getCourseSessionById(courseSessionId);

        // then
        assertEquals(responseDto, result);
        verify(fetcher).fetchCourseSessionById(courseSessionId);
        verify(mapper).toResponse(entity);
    }

    @Test
    void testCreateCourseSession_ReturnResponseDto(){
        // Given
        PostCourseSessionRequest requestDto = new PostCourseSessionRequest(
                LocalDateTime.now(),
                30,
                1L,
                1L);
        CourseSession entity = new CourseSession();
        Trainer trainer = new Trainer();
        CourseType courseType = new CourseType();
        CourseSessionResponse responseDto = new CourseSessionResponse(
                1L,
                LocalDateTime.now(),
                30,
                1L,
                1L);

        when(mapper.postRequestToEntity(requestDto)).thenReturn(entity);
        when(fetcher.fetchCourseTypeById(1L)).thenReturn(courseType);
        when(fetcher.fetchTrainerById(1L)).thenReturn(trainer);
        when(repository.save(entity)).thenReturn(entity);
        when(mapper.toResponse(entity)).thenReturn(responseDto);
        when(validator.isTrainerAlreadyBooked(entity)).thenReturn(false);

        // When
        CourseSessionResponse result = service.createCourseSession(requestDto);

        // Then
        assertEquals(responseDto, result);
        verify(mapper).postRequestToEntity(requestDto);
        verify(fetcher).fetchCourseTypeById(1L);
        verify(fetcher).fetchTrainerById(1L);
        verify(repository).save(entity);
        verify(mapper).toResponse(entity);
        verify(validator).isTrainerAlreadyBooked(entity);
    }

    @Test
    void testDeleteCourseSessionById_ReturnResponseDto(){
        // Given
        Long courseSessionId = 1L;
        CourseSessionResponse responseDto = new CourseSessionResponse(
                1L,
                LocalDateTime.now(),
                45,
                2L,
                3L);
        CourseSession entity = new CourseSession();

        when(mapper.toResponse(entity)).thenReturn(responseDto);
        when(fetcher.fetchCourseSessionById(courseSessionId)).thenReturn(entity);


        // When
        CourseSessionResponse result = service.deleteCourseSessionById(1L);

        // Then
        assertEquals(responseDto,result);
        verify(fetcher).fetchCourseSessionById(courseSessionId);
        verify(repository).delete(entity);
        verify(mapper).toResponse(entity);
    }
}