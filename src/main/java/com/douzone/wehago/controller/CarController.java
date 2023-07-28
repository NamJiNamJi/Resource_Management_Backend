package com.douzone.wehago.controller;

import com.douzone.wehago.common.Response;
import com.douzone.wehago.dto.CarDTO;
import com.douzone.wehago.dto.CarPageResponseDTO;
import com.douzone.wehago.dto.CarResponseDTO;
import com.douzone.wehago.service.CarService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@AllArgsConstructor
@RequestMapping("/api/car")
public class CarController {

    private final CarService carService;

    @PostMapping
    public ResponseEntity<Object> saveCar(@RequestPart(value = "data") CarDTO carDTO,
                                          @RequestPart(value = "image", required = false) MultipartFile image) throws IOException {

        CarResponseDTO carResponseDTO = carService.saveCar(carDTO, image);
        Response response = new Response(HttpStatus.CREATED, "등록 성공", carResponseDTO);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Object> CarfindAll() {

        CarPageResponseDTO carPageResponseDTO = carService.findAllCar();

        return new ResponseEntity<>(carPageResponseDTO, HttpStatus.OK);

    }
}