package com.reservation.api.service;

import com.reservation.api.entity.Room;
import com.reservation.api.exception.BadRequestException;
import com.reservation.api.exception.NotFoundException;
import com.reservation.api.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {
    private final RoomRepository repo;
    public RoomService(RoomRepository repo){this.repo = repo ; }

    public List<Room> listAll() {return repo.findAll(); }
    public Room findById(Long id) {
        return repo.findById(id).orElseThrow(() -> new NotFoundException("Room not found"));
    }

    public Room create(Room r) {
        if (r.getCapacity() <= 0) throw new BadRequestException("Capacity must be > 0");
        return repo.save(r);
    }
    public Room update(Long id, Room update) {
        Room existing = findById(id);
        existing.setName(update.getName());
        existing.setCapacity(update.getCapacity());
        existing.setActive(update.isActive());
        return repo.save(existing);
    }
    public void delete(Long id) {
        Room existing = findById(id);
        repo.delete(existing);
    }
}
