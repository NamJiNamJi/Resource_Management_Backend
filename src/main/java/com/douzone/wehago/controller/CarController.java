package com.douzone.wehago.controller;

import com.douzone.wehago.domain.Car;
import com.douzone.wehago.dto.CarSaveResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class CarController {

    @PostMapping("cars")
    @ResponseBody
    public CarSaveResponse borrow(@RequestBody Car car){
        log.info("{}",car);
        CarSaveResponse carSaveResponse = CarSaveResponse.builder()
                .ok("ok")
                .build();
        return carSaveResponse;
    }
}
