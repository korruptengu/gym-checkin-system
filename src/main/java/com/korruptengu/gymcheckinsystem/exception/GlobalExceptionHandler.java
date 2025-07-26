package com.korruptengu.gymcheckinsystem.exception;

import com.korruptengu.gymcheckinsystem.dto.error.ApiError;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    // ========== Allgemeine Fehler ========== //

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiError> handleIllegalArgument(IllegalArgumentException ex, HttpServletRequest request) {
        return ResponseEntity.badRequest().body(buildApiError(HttpStatus.BAD_REQUEST, ex.getMessage(), request));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiError> handleEntityNotFound(EntityNotFoundException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(buildApiError(HttpStatus.NOT_FOUND, ex.getMessage(), request));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGenericException(Exception ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(buildApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), request));
    }

    // ========== NotFound Exceptions ========== //

    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity<ApiError> handleMemberNotFound(MemberNotFoundException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(buildApiError(HttpStatus.NOT_FOUND, ex.getMessage(), request));
    }

    @ExceptionHandler(AppUserNotFoundException.class)
    public ResponseEntity<ApiError> handleAppUserNotFound(AppUserNotFoundException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(buildApiError(HttpStatus.NOT_FOUND, ex.getMessage(), request));
    }

    @ExceptionHandler(TrainerNotFoundException.class)
    public ResponseEntity<ApiError> handleTrainerNotFound(TrainerNotFoundException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(buildApiError(HttpStatus.NOT_FOUND, ex.getMessage(), request));
    }

    @ExceptionHandler(CheckInNotFoundException.class)
    public ResponseEntity<ApiError> handleCheckInNotFound(CheckInNotFoundException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(buildApiError(HttpStatus.NOT_FOUND, ex.getMessage(), request));
    }

    @ExceptionHandler(CourseTypeNotFoundException.class)
    public ResponseEntity<ApiError> handleCourseTypeNotFound(CourseTypeNotFoundException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(buildApiError(HttpStatus.NOT_FOUND, ex.getMessage(), request));
    }

    @ExceptionHandler(CourseSessionNotFoundException.class)
    public ResponseEntity<ApiError> handleCourseSessionTypeNotFound(CourseSessionNotFoundException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(buildApiError(HttpStatus.NOT_FOUND, ex.getMessage(), request));
    }

    @ExceptionHandler(TrainingSessionNotFoundException.class)
    public ResponseEntity<ApiError> handleTrainingSessionNotFound(TrainingSessionNotFoundException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(buildApiError(HttpStatus.NOT_FOUND, ex.getMessage(), request));
    }

    @ExceptionHandler(CourseBookingNotFoundException.class)
    public ResponseEntity<ApiError> handleCourseBookingNotFound(CourseBookingNotFoundException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(buildApiError(HttpStatus.NOT_FOUND, ex.getMessage(), request));
    }

    @ExceptionHandler(TrainTeacherNotFoundException.class)
    public ResponseEntity<ApiError> handleTrainTeacherNotFoundException(TrainTeacherNotFoundException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(buildApiError(HttpStatus.NOT_FOUND, ex.getMessage(), request));
    }

    // ========== Conflict und Bad Request ========== //

    @ExceptionHandler(DuplicateTrainTeacherException.class)
    public ResponseEntity<ApiError> handleDuplicateTrainTeacherException(DuplicateTrainTeacherException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(buildApiError(HttpStatus.CONFLICT, ex.getMessage(), request));
    }

    @ExceptionHandler(EmptyUpdateDataException.class)
    public ResponseEntity<ApiError> handleEmptyUpdateDataException(EmptyUpdateDataException ex, HttpServletRequest request) {
        return ResponseEntity.badRequest().body(buildApiError(HttpStatus.BAD_REQUEST, ex.getMessage(), request));
    }

    @ExceptionHandler(IllegalPutRequestException.class)
    public ResponseEntity<ApiError> handleIllegalPutRequest(IllegalPutRequestException ex, HttpServletRequest request) {
        return ResponseEntity.badRequest().body(buildApiError(HttpStatus.BAD_REQUEST, ex.getMessage(), request));
    }

    @ExceptionHandler(IllegalPatchRequestException.class)
    public ResponseEntity<ApiError> handleIllegalPatchRequest(IllegalPatchRequestException ex, HttpServletRequest request) {
        return ResponseEntity.badRequest().body(buildApiError(HttpStatus.BAD_REQUEST, ex.getMessage(), request));
    }

    @ExceptionHandler(IllegalPostRequestException.class)
    public ResponseEntity<ApiError> handleIllegalPostRequest(IllegalPostRequestException ex, HttpServletRequest request) {
        return ResponseEntity.badRequest().body(buildApiError(HttpStatus.BAD_REQUEST, ex.getMessage(), request));
    }

    @ExceptionHandler(RoleConflictException.class)
    public ResponseEntity<ApiError> handleRoleConflictException(RoleConflictException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(buildApiError(HttpStatus.CONFLICT, ex.getMessage(), request));
    }

    // ========== Hilfsmethode ========== //

    private ApiError buildApiError(HttpStatus status, String message, HttpServletRequest request) {
        return new ApiError(
                status.value(),
                status.getReasonPhrase(),
                message,
                request.getRequestURI(),
                LocalDateTime.now()
        );
    }
}
