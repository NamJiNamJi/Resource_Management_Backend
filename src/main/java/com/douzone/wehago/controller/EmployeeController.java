package com.douzone.wehago.controller;

import com.douzone.wehago.dto.EmployeeDTO;
import com.douzone.wehago.dto.EmployeeResponseDTO;
import com.douzone.wehago.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Object> saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        EmployeeResponseDTO employeeResponseDTO = employeeService.saveEmployee(employeeDTO);

        return new ResponseEntity<>(employeeResponseDTO, HttpStatus.CREATED);
    }


}
