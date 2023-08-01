package com.douzone.wehago.controller;
import com.douzone.wehago.common.Response;
import com.douzone.wehago.domain.CalendarContentTest;
import com.douzone.wehago.dto.CarDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@RestController
@AllArgsConstructor

public class CalendarController {
    @PostMapping("/api/endpoint")
    public ResponseEntity<?> calendar(@RequestPart(value = "data")CarDTO carDTO){
        System.out.println(carDTO.getCarYear());
        return ResponseEntity.ok(carDTO.getCarYear());
    }
}
