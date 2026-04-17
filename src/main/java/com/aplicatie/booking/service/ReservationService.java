package com.aplicatie.booking.service;

import com.aplicatie.booking.dto.ReservationRequest;
import com.aplicatie.booking.entity.Reservation;
import com.aplicatie.booking.entity.Room;
import com.aplicatie.booking.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aplicatie.booking.repo.ReservationRepository;
import com.aplicatie.booking.repo.RoomRepository;
import com.aplicatie.booking.repo.UserRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    private RoomRepository roomRepository;
    private UserRepository userRepository;
    private ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(RoomRepository roomRepository, UserRepository userRepository, ReservationRepository reservationRepository){
        this.reservationRepository =reservationRepository;
        this.roomRepository= roomRepository;
        this.userRepository = userRepository;
    }

    public Reservation makeReservation(ReservationRequest request) {

        Integer userId = request.getUserId();
        Integer roomId = request.getRoomId();
        LocalDate date = request.getDate();
        LocalTime start = request.getStartTime();
        LocalTime end = request.getEndTime();

        if (start.isAfter(end) || start.equals(end)) {
            throw new RuntimeException("INVALID_TIME_RANGE");
        }

        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isEmpty()) {
            throw new RuntimeException("USER_NOT_FOUND");
        }
        User user = userOptional.get();

        Optional<Room> roomOptional = roomRepository.findById(roomId);

        if (roomOptional.isEmpty()) {
            throw new RuntimeException("ROOM_NOT_FOUND");
        }
        Room room = roomOptional.get();

        if (reservationRepository.isRoomOccupied(roomId, date, start, end)) {
            throw new RuntimeException("ROOM_ALREADY_OCCUPIED");
        }

        Reservation reservation = new Reservation(room, user, start, end, date);

        user.addReservation(reservation);
        room.addReservation(reservation);

        return reservationRepository.save(reservation);
    }

    public List<Reservation> getReservationsByUserId(Integer userId) {
        return reservationRepository.findByUserId(userId);
    }

    public void cancelReservation(Integer id, Integer currentUserId) {

        Optional<Reservation> reservationOptional = reservationRepository.findById(id);

        if (reservationOptional.isEmpty()) {
            throw new RuntimeException("RESERVATION_NOT_FOUND");
        }

        Reservation res = reservationOptional.get();

        //verificam daca cel care face cancel e intr-adevar contul loggat
        if (!res.getUser().getId().equals(currentUserId)) {
            throw new RuntimeException("NOT_AUTHORIZED_TO_CANCEL");
        }

        reservationRepository.delete(res);
    }

    public List<Reservation> getReservationsByRoomAndDate(Integer roomId, LocalDate date) {
        return reservationRepository.findByRoomIdAndReservationDate(roomId, date);
    }
}
