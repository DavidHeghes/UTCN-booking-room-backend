package com.aplicatie.booking.service;

import com.aplicatie.booking.entity.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aplicatie.booking.repo.RoomRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    private RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository){
        this.roomRepository = roomRepository;
    }

    public List<Room> findAll(){
        return roomRepository.findAll();
    }

    public Room findById(Integer id){
        Optional<Room> tempRoom = roomRepository.findById(id);

        if(tempRoom.isEmpty()){
            throw  new RuntimeException("ROOM_NOT_FOUND");
        }
        return tempRoom.get();
    }

    public Room save(Room room, String email) {
        if (!email.endsWith("@admin.com")) {
            throw new RuntimeException("UNAUTHORIZED");
        }

        if (roomRepository.findByName(room.getName()).isPresent()) {
            throw new RuntimeException("ROOM_ALREADY_EXISTS");
        }
        return roomRepository.save(room);
    }

    public void deleteById(Integer id, String email) {
        if (!email.endsWith("@admin.com")) {
            throw new RuntimeException("UNAUTHORIZED");
        }

        if (!roomRepository.existsById(id)) {
            throw new RuntimeException("ROOM_NOT_FOUND");
        }
        roomRepository.deleteById(id);
    }
}