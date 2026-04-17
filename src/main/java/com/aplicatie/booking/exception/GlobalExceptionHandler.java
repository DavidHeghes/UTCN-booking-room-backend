package com.aplicatie.booking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        String msg = ex.getMessage();


        // ERORI DE USER
        if (msg.equals("USER_NOT_FOUND")) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");
        if (msg.equals("INVALID_PASSWORD")) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password!");
        if (msg.equals("EMAIL_ALREADY_EXISTS")) return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists!");
        if (msg.equals("INVALID_EMAIL_DOMAIN")) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Only @gmail.com emails are allowed!");


        // ERORI DE ROOM
        if (msg.equals("ROOM_ALREADY_EXISTS")) return ResponseEntity.status(HttpStatus.CONFLICT).body("Room name already exists!");
        if (msg.equals("ROOM_NOT_FOUND")) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Room not found!");
        if (msg.equals("UNAUTHORIZED")) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Only admins can add/delete rooms!");


        // ERORI DE RESERVATION
        if (msg.equals("INVALID_TIME_RANGE")) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Start time must be before end time!");
        if (msg.equals("OUTSIDE_ALLOWED_HOURS")) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Reservations can only be made between 17:00 and 22:00!");
        if (msg.equals("ROOM_ALREADY_OCCUPIED")) return ResponseEntity.status(HttpStatus.CONFLICT).body("Room is already occupied during this time!");
        if (msg.equals("RESERVATION_NOT_FOUND")) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reservation not found!");

        // DACA E O EROARE NECUNOSCUTA
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server error: " + msg);
    }
}