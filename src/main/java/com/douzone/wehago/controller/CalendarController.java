package com.douzone.wehago.controller;
import com.douzone.wehago.common.Response;
import com.douzone.wehago.domain.CalendarContentTest;
import com.douzone.wehago.dto.car.CarDTO;
import com.douzone.wehago.dto.reservation.ReservationDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@RestController
@AllArgsConstructor

public class CalendarController {
//    @PostMapping("/api/endpoint")
//    public ResponseEntity<?> calendar(@RequestPart(value = "data")CarDTO carDTO){
//        System.out.println(carDTO.getCarYear());
//        return ResponseEntity.ok(carDTO.getCarYear());
//    }
}
