package com.korruptengu.gymcheckinsystem.exception;

import java.time.LocalDateTime;

public class TrainerAlreadyBookedException extends RuntimeException{
    public TrainerAlreadyBookedException(Long id, LocalDateTime start, LocalDateTime end) {
        super("Trainer with Id: " + id + " is already booked between " + start + " and " + end);
    }
}

