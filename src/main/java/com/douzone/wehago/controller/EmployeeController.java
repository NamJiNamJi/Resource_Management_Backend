package com.douzone.wehago.controller;

import com.douzone.wehago.dto.*;
import com.douzone.wehago.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Object> saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        EmployeeResponseDTO employeeResponseDTO = employeeService.saveEmployee(employeeDTO);

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
    public ResponseEntity<Object> updateEmployee(@RequestBody EmployeeDTO employeeDTO, @PathVariable Integer empSeq) {
        EmployeeResponseDTO employeeResponseDTO = employeeService.updateEmployee(employeeDTO, empSeq);

        return new ResponseEntity<>(employeeResponseDTO, HttpStatus.OK);
    }


}
