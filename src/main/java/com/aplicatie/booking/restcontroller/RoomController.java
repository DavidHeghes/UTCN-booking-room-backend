package com.aplicatie.booking.restcontroller;

import com.aplicatie.booking.entity.Room;
import com.aplicatie.booking.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
@CrossOrigin(origins = "http://localhost:5173")
public class RoomController {

    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public List<Room> getAllRooms() {
        return roomService.findAll();
    }

    @PostMapping
    public Room addRoom(@RequestBody Room room, @RequestParam String email) {
        return roomService.save(room, email);
    }

    @DeleteMapping("/{id}")
    public void deleteRoom(@PathVariable Integer id, @RequestParam String email) {
        roomService.deleteById(id, email);
    }
}
