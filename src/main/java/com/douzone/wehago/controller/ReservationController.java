package com.douzone.wehago.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.douzone.wehago.common.Response;
import com.douzone.wehago.domain.Reservation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


//    @PostMapping("/api/")
//    public ResponseEntity<Object> testreservation(@RequestBody Reservation reservation){
//        System.out.println(reservation.getRsvStart() + "  +  " + reservation.getRsvEnd());
//
//
//        Response response = new Response(HttpStatus.CREATED, "등록 성공", reservation);
//        return new ResponseEntity<>(response, HttpStatus.CREATED);
//
//    }

}