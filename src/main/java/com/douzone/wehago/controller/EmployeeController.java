package com.douzone.wehago.controller;

import com.douzone.wehago.dto.employee.EmployeeDTO;
import com.douzone.wehago.dto.employee.EmployeePageResponseDTO;
import com.douzone.wehago.dto.employee.EmployeeResponseDTO;
import com.douzone.wehago.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@AllArgsConstructor
@RequestMapping("/api/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Object> saveEmployee(@RequestPart(value = "data") EmployeeDTO employeeDTO,
                                               @RequestPart(value = "image" ,required = false) MultipartFile image) throws IOException {
        EmployeeResponseDTO employeeResponseDTO = employeeService.saveEmployee(employeeDTO, image);

        return new ResponseEntity<>(employeeResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Object> findAll() {
        EmployeePageResponseDTO employeePageResponseDTO = employeeService.findAll();

        return new ResponseEntity<>(employeePageResponseDTO, HttpStatus.OK);
    }


    @GetMapping("/{empSeq}")
    public ResponseEntity<Object> findOne(@PathVariable Integer empSeq) {
        EmployeeResponseDTO employeeResponseDTO = employeeService.findOne(empSeq);

        return new ResponseEntity<>(employeeResponseDTO, HttpStatus.OK);
    }

    @PutMapping("/{empSeq}")
    public ResponseEntity<Object> updateEmployee(@RequestPart(value = "data") EmployeeDTO employeeDTO,
                                                 @RequestPart(value = "image" ,required = false) MultipartFile image,
                                                 @PathVariable Integer empSeq) throws IOException {
        EmployeeResponseDTO employeeResponseDTO = employeeService.updateEmployee(employeeDTO, image, empSeq);

        return new ResponseEntity<>(employeeResponseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{empSeq}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable Integer empSeq) {
        employeeService.deleteEmployee(empSeq);
        String message = "삭제 성공";

        return new ResponseEntity<>(message, HttpStatus.OK);
    }


}
