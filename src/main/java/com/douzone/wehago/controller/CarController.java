package com.douzone.wehago.controller;

import com.douzone.wehago.common.Response;
import com.douzone.wehago.dto.car.CarDTO;
import com.douzone.wehago.dto.car.CarPageResponseDTO;
import com.douzone.wehago.dto.car.CarResponseDTO;
import com.douzone.wehago.service.CarService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/api/car")
public class CarController {

    private final CarService carService;

    @PostMapping
    public ResponseEntity<Object> saveCar(@RequestPart(value = "data") CarDTO carDTO,
                                          @RequestPart(value = "image", required = false) MultipartFile image)
            throws IOException {

        CarResponseDTO carResponseDTO = carService.saveCar(carDTO, image);
        Response response = new Response(HttpStatus.CREATED, "등록 성공", carResponseDTO);

        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<Object> CarfindAll() {

        CarPageResponseDTO carPageResponseDTO = carService.findAllCar();

        return new ResponseEntity<>(carPageResponseDTO, HttpStatus.OK);

    }

    @GetMapping("/{carSeq}")
    public ResponseEntity<Object> findOneCar(@PathVariable Integer carSeq) {
        CarResponseDTO carResponseDTO = carService.findOneCar(carSeq);

        return  new ResponseEntity<>(carResponseDTO, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Object> searchCar (@RequestParam(value = "queryType") String queryType,
                                             @RequestParam(value = "searchString") String searchString) {
        log.info(queryType + searchString);
        CarPageResponseDTO carPageResponseDTO = carService.searchCar(queryType, searchString);
        Response response = new Response(HttpStatus.OK, "차량 검색 성공", carPageResponseDTO);

        return  new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/{carSeq}")
    public ResponseEntity<Object> updateCar (@RequestPart(value = "data") CarDTO carDTO,
                                             @RequestPart(value = "image", required = false) MultipartFile image,
                                             @PathVariable Integer carSeq) throws IOException {

        CarResponseDTO carResponseDTO = carService.updateCar(carDTO, image, carSeq);
        Response response = new Response(HttpStatus.OK, "등록 성공", carResponseDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PostMapping("/del/{carSeq}")
    public ResponseEntity<Object> deleteCar(@PathVariable Integer carSeq) {

        carService.deleteCar(carSeq);
        Response response = new Response(HttpStatus.OK, "차량 삭제 성공", null);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

//    @DeleteMapping("/{carSeq}")
//    public ResponseEntity<Object> deleteCar(@PathVariable Integer carSeq) {
//        carService.deleteCar(carSeq);
//        String message = "삭제 성공";
//
//        return new ResponseEntity<>(message, HttpStatus.OK);
//    }
}