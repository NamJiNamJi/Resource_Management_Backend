package com.douzone.wehago.controller;

import com.douzone.wehago.common.Response;
import com.douzone.wehago.dto.FacilityDTO;
import com.douzone.wehago.dto.FacilityPageResponseDTO;
import com.douzone.wehago.dto.FacilityResponseDTO;
import com.douzone.wehago.service.FacilityService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/car")
public class FacilityController {

    private FacilityService facilityService;

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody FacilityDTO facilityDTO) {

        FacilityResponseDTO facilityResponseDTO = facilityService.save(facilityDTO);
        Response response = new Response(HttpStatus.CREATED, "차량 자원 등록 성공", facilityResponseDTO);

        return  new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Object> findAll () {

        FacilityPageResponseDTO facilityPageResponseDTO = facilityService.findAll();

        return new ResponseEntity<>(facilityPageResponseDTO, HttpStatus.OK);
    }
}
