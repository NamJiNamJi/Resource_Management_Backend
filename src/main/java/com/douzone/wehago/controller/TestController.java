package com.douzone.wehago.controller;

import com.douzone.wehago.common.Response;
import com.douzone.wehago.dto.TestDTO;
import com.douzone.wehago.dto.TestResponseDTO;
import com.douzone.wehago.service.TestService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@AllArgsConstructor
@RequestMapping("api/test")
public class TestController {
    private TestService testService;

    @PostMapping
    public ResponseEntity<Object> saveEmployee(@RequestPart(value = "data") TestDTO testDTO,
                                               @RequestPart(value = "image" ,required = false) MultipartFile image) throws IOException {
        TestResponseDTO testResponseDTO = testService.saveTest(testDTO, image);
        Response response = new Response(HttpStatus.CREATED, "테스트 등록 성공", testResponseDTO);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
