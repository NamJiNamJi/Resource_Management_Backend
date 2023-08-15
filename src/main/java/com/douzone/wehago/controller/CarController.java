package com.douzone.wehago.controller;

import com.douzone.wehago.common.Response;
import com.douzone.wehago.dto.car.CarDTO;
import com.douzone.wehago.dto.car.CarPageResponseDTO;
import com.douzone.wehago.dto.car.CarResponseDTO;
import com.douzone.wehago.dto.reservation.ReservationDTO;
import com.douzone.wehago.service.CarService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/api/car")
public class CarController {

    private final CarService carService;

    @PostMapping("/carsearch")
    public ResponseEntity<Object> CarRsvList(@RequestBody ReservationDTO reservationDTO,
                                             @AuthenticationPrincipal UserDetails userDetails) {

        System.out.println(reservationDTO.getRsvStart());
        System.out.println(reservationDTO.getRsvEnd());

        CarPageResponseDTO carPageResponseDTO = carService.findcarList(reservationDTO, userDetails);
        Response response = new Response(HttpStatus.CREATED, "조회 성공", carPageResponseDTO);

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> saveCar(@RequestPart(value = "data") CarDTO carDTO,
                                          @RequestPart(value = "image", required = false) MultipartFile image,
                                          @AuthenticationPrincipal UserDetails userDetails)
            throws IOException {

        CarResponseDTO carResponseDTO = carService.saveCar(carDTO, image, userDetails);
        Response response = new Response(HttpStatus.CREATED, "등록 성공", carResponseDTO);

        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<Object> CarfindAll() {

        CarPageResponseDTO carPageResponseDTO = carService.findAllCar();
        Response response = new Response(HttpStatus.OK, "차량 전체 조회 성공", carPageResponseDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

//    @GetMapping("/{carSeq}")
//    public ResponseEntity<Object> findOneCar(@PathVariable Integer carSeq) {
//
//        CarResponseDTO carResponseDTO = carService.findOneCar(carSeq);
//        Response response = new Response(HttpStatus.OK, "차량 검색 성공", carResponseDTO);
//
//        return  new ResponseEntity<>(response, HttpStatus.OK);
//    }

    @GetMapping("/search")
    public ResponseEntity<Object> searchCar (@RequestParam(value = "columnName") String columnName,
                                             @RequestParam(value = "searchString") String searchString) {
        log.info(columnName + searchString);
        CarPageResponseDTO carPageResponseDTO = carService.searchCar(columnName, searchString);
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

        Integer result = carService.deleteCar(carSeq);
        Response response = new Response(HttpStatus.OK, "차량 삭제 성공", result);

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