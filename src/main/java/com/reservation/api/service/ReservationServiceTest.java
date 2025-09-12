// src/test/java/com/reservation/api/service/ReservationServiceTest.java
package com.reservation.api.service;

import com.reservation.api.entity.*;
import com.reservation.api.exception.BadRequestException;
import com.reservation.api.repository.*;
import org.junit.jupiter.api.*;
import org.mockito.*;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReservationServiceTest {
    @Mock private ReservationRepository reservationRepo;
    @Mock private RoomRepository roomRepo;
    @Mock private UserRepository userRepo;
    @Mock private ReservationHistoricRepository historicRepo;

    private ReservationService service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        service = new ReservationService(reservationRepo, roomRepo, userRepo, historicRepo);
    }

    @Test
    void createReservation_conflict_throws() {
        Room room = new Room(); room.setId(1L); room.setActive(true); room.setCapacity(5);
        User user = new User(); user.setId(1L);
        LocalDateTime start = LocalDateTime.now().plusHours(1);
        LocalDateTime end = start.plusHours(2);

        when(roomRepo.findById(1L)).thenReturn(Optional.of(room));
        when(userRepo.findById(1L)).thenReturn(Optional.of(user));
        // simulate conflict
        when(reservationRepo.findByRoomAndEndTimeGreaterThanAndStartTimeLessThanAndStatus(eq(room), any(), any(), eq(ReservationStatus.ACTIVE)))
                .thenReturn(List.of(new Reservation()));

        assertThrows(BadRequestException.class, () -> service.create(1L, 1L, start, end));
    }
}
