package com.aplicatie.booking.restcontroller;

import com.aplicatie.booking.entity.Reservation;
import com.aplicatie.booking.dto.ReservationRequest;
import com.aplicatie.booking.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/reservations")
@CrossOrigin(origins = "http://localhost:5173")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public Reservation bookRoom(@RequestBody ReservationRequest request) {
        return reservationService.makeReservation(request);
    }

    @GetMapping("/user/{userId}")
    public List<Reservation> getReservationsByUser(@PathVariable Integer userId) {
        return reservationService.getReservationsByUserId(userId);
    }

    @DeleteMapping("/{id}")
    public void cancelReservation(@PathVariable Integer id, @RequestParam Integer userId) {
        reservationService.cancelReservation(id, userId);
    }

    @GetMapping("/room/{roomId}/date/{date}")
    public List<Reservation> getReservationsForRoomAndDate(
            @PathVariable Integer roomId,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return reservationService.getReservationsByRoomAndDate(roomId, date);
    }
}