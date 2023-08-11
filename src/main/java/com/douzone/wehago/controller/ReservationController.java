package com.douzone.wehago.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.douzone.wehago.common.Response;
import com.douzone.wehago.domain.Reservation;
import com.douzone.wehago.dto.reservation.ReservationDTO;
import com.douzone.wehago.service.ReservationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

@RestController
@Slf4j
@AllArgsConstructor
public class ReservationController {

    private ReservationService reservationService;

    // 예약 등록
    @PostMapping("/api/reservation")
    public ResponseEntity<?> registerEvent(@RequestBody ReservationDTO reservationDTO,
                                           @AuthenticationPrincipal UserDetails userDetails) {

        reservationService.reservationEvent(reservationDTO, userDetails);

        return null;
    }

}