package com.douzone.wehago.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.douzone.wehago.common.Response;
import com.douzone.wehago.domain.Reservation;
import com.douzone.wehago.dto.reservation.ReservationPageResponseDTO;
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

    private final ReservationService reservationService;


    @GetMapping("/api/reservationlist")
    public ResponseEntity<Object> reservationList(@RequestParam(defaultValue = "1") Integer pageNum,
                                                  @RequestParam(defaultValue = "3") Integer pageSize,
                                                  @AuthenticationPrincipal UserDetails userDetails){
        ReservationPageResponseDTO reservationPageResponseDTO = reservationService.reservationList(pageNum,pageSize,userDetails);
        Response response = new Response(HttpStatus.OK, "나의 예약 조회 성공", reservationPageResponseDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}