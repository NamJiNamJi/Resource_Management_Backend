package com.douzone.wehago.controller;

import com.douzone.wehago.common.Response;
import com.douzone.wehago.dto.*;
import com.douzone.wehago.service.DeviceService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@AllArgsConstructor
@RequestMapping("/api/device")
public class DeviceController {

    private final DeviceService deviceService;

    @PostMapping
    public ResponseEntity<Object> saveDeivce (@RequestPart(value = "data") DeviceDTO deviceDTO,
                                              @RequestPart(value = "image", required = false) MultipartFile image) throws IOException {

        DeviceResponseDTO deviceResponseDTO = deviceService.saveDevice(deviceDTO, image);
        Response response = new Response(HttpStatus.CREATED, "등록 성공", deviceResponseDTO);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Object> DevicefindAll() {

        DevicePageResponseDTO devicePageResponseDTO = deviceService.findAllDevice();

        return new ResponseEntity<>(devicePageResponseDTO, HttpStatus.OK);

    }

    @GetMapping("/{dvcSeq}")
    public ResponseEntity<Object> findOneDevice (@PathVariable Integer dvcSeq) {
        DeviceResponseDTO deviceResponseDTO = deviceService.findOneDevice(dvcSeq);

        return new ResponseEntity<>(deviceResponseDTO, HttpStatus.OK);
    }

    @PostMapping("/{dvcSeq}")
    public ResponseEntity<Object> updateDevice (@RequestPart(value = "data") DeviceDTO deviceDTO,
                                                @RequestPart(value = "image", required = false) MultipartFile image,
                                                @PathVariable Integer dvcSeq) throws IOException {

        DeviceResponseDTO deviceResponseDTO = deviceService.updateDevice(deviceDTO, image, dvcSeq);
        Response response = new Response(HttpStatus.OK, "등록 성공", deviceResponseDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @DeleteMapping("/{dvcSeq}")
    public ResponseEntity<Object> deleteDevice(@PathVariable Integer dvcSeq) {
        deviceService.deleteDevice(dvcSeq);
        String message = "삭제 성공";

        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
