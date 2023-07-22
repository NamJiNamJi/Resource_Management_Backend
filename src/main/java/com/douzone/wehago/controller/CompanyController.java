package com.douzone.wehago.controller;

import com.douzone.wehago.dto.CompanyDTO;
import com.douzone.wehago.dto.CompanyResponseDTO;
import com.douzone.wehago.service.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    @PostMapping("api/company")
    public ResponseEntity<Object> save(@RequestBody CompanyDTO companyDTO) {
        CompanyResponseDTO companyResponseDTO = companyService.save(companyDTO);

        return new ResponseEntity<>(companyResponseDTO, HttpStatus.CREATED);
    }




}
