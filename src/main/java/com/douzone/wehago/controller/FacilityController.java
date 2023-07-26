package com.douzone.wehago.controller;

import com.douzone.wehago.domain.Facility;
import com.douzone.wehago.dto.FacilityDTO;
import com.douzone.wehago.mapper.FacilityMapper;
import com.douzone.wehago.service.FacilityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.List;

@Slf4j
@RestController
public class FacilityController {

    @Autowired
    private FacilityService facilityService;

    @ResponseBody
    @PostMapping("/FacilitySaveModal")
    public String saveFacility(@RequestBody FacilityDTO facilityDTO) {
        Facility facility = new Facility();
        // FacilityDTO에서 Facility로 변환하여 저장
        facility.setCarName(facilityDTO.getCarName());
        facility.setCarNumber(facilityDTO.getCarNumber());
        facility.setCarDistance(facilityDTO.getCarDistance());
        facility.setCarYear(facilityDTO.getCarYear());
        facility.setCarExplan(facilityDTO.getCarExplan());
//        // 이미지 데이터를 디코딩하여 byte 배열로 변환
//        byte[] carImage = Base64.getDecoder().decode(facilityDTO.getCarImage());
//        facility.setCarImage(carImage);

        facilityService.save(facility);

        return "입력";
    }

    @GetMapping("/FacilitySaveModal")
    @ResponseBody
    public List<Facility> findAll() {

        return facilityService.findAll();
    }
}
