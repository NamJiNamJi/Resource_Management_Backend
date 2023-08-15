package com.douzone.wehago.controller;

import com.douzone.wehago.common.Response;
import com.douzone.wehago.dto.car.CarPageResponseDTO;
import com.douzone.wehago.dto.device.DeviceDTO;
import com.douzone.wehago.dto.device.DevicePageResponseDTO;
import com.douzone.wehago.dto.device.DeviceResponseDTO;
import com.douzone.wehago.dto.reservation.ReservationDTO;
import com.douzone.wehago.service.DeviceService;
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
@RequestMapping("/api/device")
public class DeviceController {

    private final DeviceService deviceService;
    @PostMapping("/devicesearch")
    public ResponseEntity<Object> deviceRsvList(@RequestBody ReservationDTO reservationDTO,
                                                @AuthenticationPrincipal UserDetails userDetails){
        System.out.println(reservationDTO.getRsvStart());
        DevicePageResponseDTO devicePageResponseDTO = deviceService.finddeviceList(reservationDTO,userDetails);
        Response response = new Response(HttpStatus.CREATED, "조회 성공", devicePageResponseDTO);

        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Object> saveDeivce (@RequestPart(value = "data") DeviceDTO deviceDTO,
                                              @RequestPart(value = "image", required = false) MultipartFile image,
                                              @AuthenticationPrincipal UserDetails userDetails) throws IOException {

        DeviceResponseDTO deviceResponseDTO = deviceService.saveDevice(deviceDTO, image, userDetails);
        Response response = new Response(HttpStatus.CREATED, "등록 성공", deviceResponseDTO);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Object> DevicefindAll(@AuthenticationPrincipal UserDetails userDetails) {

        DevicePageResponseDTO devicePageResponseDTO = deviceService.findAllDevice(userDetails);
        Response response = new Response(HttpStatus.OK, "기기 전체 조회 성공", devicePageResponseDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

//    @GetMapping("/{dvcSeq}")
//    public ResponseEntity<Object> findOneDevice (@PathVariable Integer dvcSeq) {
//        DeviceResponseDTO deviceResponseDTO = deviceService.findOneDevice(dvcSeq);
//
//        return new ResponseEntity<>(deviceResponseDTO, HttpStatus.OK);
//    }

    @GetMapping("/search")
    public ResponseEntity<Object> searchDevice (@RequestParam(value = "columnName") String columnName,
                                                @RequestParam(value = "searchString") String searchString,
                                                @AuthenticationPrincipal UserDetails userDetails) {
        log.info(columnName + searchString);
        DevicePageResponseDTO devicePageResponseDTO = deviceService.searchDevice(columnName, searchString, userDetails);
        Response response = new Response(HttpStatus.OK, "기기 검색 성공", devicePageResponseDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PostMapping("/{dvcSeq}")
    public ResponseEntity<Object> updateDevice (@RequestPart(value = "data") DeviceDTO deviceDTO,
                                                @RequestPart(value = "image", required = false) MultipartFile image,
                                                @PathVariable Integer dvcSeq,
                                                @AuthenticationPrincipal UserDetails userDetails) throws IOException {

        DeviceResponseDTO deviceResponseDTO = deviceService.updateDevice(deviceDTO, image, dvcSeq, userDetails);
        Response response = new Response(HttpStatus.OK, "등록 성공", deviceResponseDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PostMapping("/del/{dvcSeq}")
    public ResponseEntity<Object> deleteDevice(@PathVariable Integer dvcSeq,
                                               @AuthenticationPrincipal UserDetails userDetails) {

        Integer result = deviceService.deleteDevice(dvcSeq, userDetails);
        Response response = new Response(HttpStatus.OK, "모바일기기 삭제 성공", result);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
