package com.reservation.api.service;

import com.reservation.api.entity.*;
import com.reservation.api.exception.*;
import com.reservation.api.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepo;
    private final RoomRepository roomRepo;
    private final UserRepository userRepo;
    private final ReservationHistoricRepository historicRepo;

    public ReservationService(ReservationRepository reservationRepo, RoomRepository roomRepo,
                              UserRepository userRepo, ReservationHistoricRepository historicRepo) {
        this.reservationRepo = reservationRepo;
        this.roomRepo = roomRepo;
        this.userRepo = userRepo;
        this.historicRepo = historicRepo;
    }

    @Transactional
    public Reservation create(Long roomId, Long userId, LocalDateTime start, LocalDateTime end) {
        if (start.isAfter(end) || start.isEqual(end)) throw new BadRequestException("Start must be before end");

        Room room = roomRepo.findById(roomId).orElseThrow(() -> new NotFoundException("Room not found"));
        User user = userRepo.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));

        if (!room.isActive()) throw new BadRequestException("Room inactive");
        if (room.getCapacity() <= 0) throw new BadRequestException("Room capacity invalid");

        List<Reservation> conflicts = reservationRepo.findByRoomAndEndTimeGreaterThanAndStartTimeLessThanAndStatus(
                room, start, end, ReservationStatus.ACTIVE);
        if (!conflicts.isEmpty()) throw new BadRequestException("Room already reserved in this period");

        Reservation r = new Reservation();
        r.setRoom(room);
        r.setUser(user);
        r.setStartTime(start);
        r.setEndTime(end);
        r.setStatus(ReservationStatus.ACTIVE);
        Reservation saved = reservationRepo.save(r);

        ReservationHistoric hist = new ReservationHistoric();
        hist.setReservationId(saved.getId());
        hist.setAction("CREATED");
        hist.setActionTime(LocalDateTime.now());
        historicRepo.save(hist);

        return saved;
    }

    @Transactional
    public Reservation cancel(Long reservationId) {
        Reservation r = reservationRepo.findById(reservationId).orElseThrow(() -> new NotFoundException("Reservation not found"));
        if (r.getStatus() == ReservationStatus.CANCELED) throw new BadRequestException("Reservation already canceled");
        r.setStatus(ReservationStatus.CANCELED);
        Reservation saved = reservationRepo.save(r);

        ReservationHistoric hist = new ReservationHistoric();
        hist.setReservationId(saved.getId());
        hist.setAction("CANCELED");
        hist.setActionTime(LocalDateTime.now());
        historicRepo.save(hist);

        return saved;
    }

    public List<Reservation> listAll(){ return reservationRepo.findAll(); }
    public Reservation findById(Long id){ return reservationRepo.findById(id).orElseThrow(() -> new NotFoundException("Reservation not found")); }
}
