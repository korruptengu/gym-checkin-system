package com.korruptengu.gymcheckinsystem.constants;

public class ApiPaths {
    public static final String ID = "/{id}";
    public static final String MEMBERS = "/api/members";
    public static final String TRAINERS = "/api/trainers";
    public static final String COURSE_TYPES = "/api/courseTypes";
    public static final String CHECK_INS = "/api/checkIns";
    public static final String COURSE_SESSIONS = "/api/courseSessions";
    public static final String COURSE_BOOKINGS = "/api/courseBookings";
    public static final String COURSE_BOOKING_COMPOSITE_ID = "/{memberId}/{courseSessionId}";
    public static final String TRAINING_SESSIONS = "/api/trainingSessions";
    public static final String TRAIN_TEACHERS = "/api/trainTeachers";
    public static final String TRAIN_TEACHER_COMPOSITE_ID = "/{instructorId}/{studentId}";
    public static final String ROOT = "/api";
}
