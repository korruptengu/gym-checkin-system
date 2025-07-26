package com.korruptengu.gymcheckinsystem.exception;

import com.korruptengu.gymcheckinsystem.dto.error.ApiError;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
     private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException ex){
        log.error("Bad Request error occurred", ex);
        return ResponseEntity
                .badRequest()
                .body("Bad Request: " + ex.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFound(EntityNotFoundException ex){
        log.error("Entity not found error occurred", ex);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Not Found: " + ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex){
        log.error("Unexpected error occurred", ex);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Internal Server Error: " + ex.getMessage());
    }


    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity<String> handleMemberNotFoundException(MemberNotFoundException ex){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    /*
    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity<ApiError> handleMemberNotFound(MemberNotFoundException ex, HttpServletRequest request){
        ApiError error = buildApiError(HttpStatus.NOT_FOUND, ex.getMessage(), request);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(error);
    }
    */

    @ExceptionHandler(AppUserNotFoundException.class)
    public ResponseEntity<String> handleAppUserNotFound(AppUserNotFoundException ex){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(TrainerNotFoundException.class)
    public ResponseEntity<String> handleTrainerNotFound(TrainerNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(CheckInNotFoundException.class)
    public ResponseEntity<String> handleCheckInNotFound(CheckInNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(CourseTypeNotFoundException.class)
    public ResponseEntity<String> handleCourseTypeNotFound(CourseTypeNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(CourseSessionNotFoundException.class)
    public ResponseEntity<String> handleCourseSessionTypeNotFound(CourseSessionNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(TrainingSessionNotFoundException.class)
    public ResponseEntity<String> handleTrainingSessionNotFound(TrainingSessionNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(CourseBookingNotFoundException.class)
    public ResponseEntity<String> handleCourseBookingNotFound(CourseBookingNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(TrainTeacherNotFoundException.class)
    public ResponseEntity<String> handleTrainTeacherNotFoundException(TrainTeacherNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(DuplicateTrainTeacherException.class)
    public ResponseEntity<String> handleDuplicateTrainTeacherException(DuplicateTrainTeacherException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ex.getMessage());
    }

    @ExceptionHandler(EmptyUpdateDataException.class)
    public ResponseEntity<String> handleEmptyUpdateDataException(EmptyUpdateDataException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

    @ExceptionHandler(IllegalPutRequestException.class)
    public ResponseEntity<String> handleIllegalPutRequest(IllegalPutRequestException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

    @ExceptionHandler(IllegalPatchRequestException.class)
    public ResponseEntity<String> handleIllegalPatchRequest(IllegalPatchRequestException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

    @ExceptionHandler(IllegalPostRequestException.class)
    public ResponseEntity<String> handleIllegalPostRequest(IllegalPostRequestException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

    @ExceptionHandler(RoleConflictException.class)
    public ResponseEntity<String> handleRoleConflictException(RoleConflictException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ex.getMessage());
    }

    // Hilfsmethode für Fehlerobjekt
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
