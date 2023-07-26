package com.douzone.wehago.controller;


import com.douzone.wehago.dto.DeviceDTO;
import com.douzone.wehago.dto.DeviceSaveResponse;
import com.douzone.wehago.domain.Device;
import com.douzone.wehago.repository.DeviceRepository;
import com.douzone.wehago.service.DeviceService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;

@Slf4j
@Controller
public class DeviceController {
    private final DeviceRepository deviceRepository;

    @Autowired
    public DeviceController(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }
    @PostMapping("/DeviceSaveModal")
    @ResponseBody
    public DeviceSaveResponse borrow(@RequestBody @Valid DeviceDTO device, BindingResult bindingResult ){

        if (bindingResult.hasErrors()) {

            log.info("컨트롤러로 받은 값 : {}", device);
            // 에러 메시지 출력
            for (FieldError error : bindingResult.getFieldErrors()) {
                DeviceSaveResponse deviceSaveResponse = DeviceSaveResponse.builder()
                        .fail("Field: " + error.getField() + " Message: " + error.getDefaultMessage())
                        .message("기기 등록 실패")
                        .build();
                return deviceSaveResponse;
            }
        }
        log.info("{}", device);
        DeviceSaveResponse deviceSaveResponse = DeviceSaveResponse.builder()
                .ok("ok")
                .message("기기 등록 성공")
                .build();
        deviceRepository.insertDevice(device);
        return deviceSaveResponse;
    }
    @GetMapping("/DeviceSelectAll")
    @ResponseBody
    public List <DeviceDTO> SelectAll() {
        List<DeviceDTO> devices = deviceRepository.findAll();
        log.info("{}", devices);
        return devices;
    }

}
