package com.aplicatie.booking.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReservationRequest {

    private Integer userId;
    private Integer roomId;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }

    public Integer getRoomId() { return roomId; }
    public void setRoomId(Integer roomId) { this.roomId = roomId; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public LocalTime getStartTime() { return startTime; }
    public void setStartTime(LocalTime startTime) { this.startTime = startTime; }

    public LocalTime getEndTime() { return endTime; }
    public void setEndTime(LocalTime endTime) { this.endTime = endTime; }
}