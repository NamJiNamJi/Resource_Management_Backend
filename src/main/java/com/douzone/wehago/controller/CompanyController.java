package com.douzone.wehago.controller;

import com.douzone.wehago.dto.CompanyDTO;
import com.douzone.wehago.dto.CompanyPageResponseDTO;
import com.douzone.wehago.dto.CompanyResponseDTO;
import com.douzone.wehago.service.CompanyService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/company")
public class CompanyController {
    private final CompanyService companyService;

    // 회사 생성
    @PostMapping
    public ResponseEntity<Object> save(@RequestBody CompanyDTO companyDTO) {
        CompanyResponseDTO companyResponseDTO = companyService.save(companyDTO);

        return new ResponseEntity<>(companyResponseDTO, HttpStatus.CREATED);
    }

    // 회사 전체 조회
//    @GetMapping
////    public ResponseEntity<Object> findAll(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize) {
//        public void findAll(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize) {
//
////            CompanyPageResponseDTO companyPageResponseDTO = companyService.findAll(pageNum, pageSize);
//
//              companyService.findAll(pageNum, pageSize);
//    }


    @GetMapping
    public ResponseEntity<Object> findAll() {
        CompanyPageResponseDTO companyPageResponseDTO = companyService.findAll();

        return new ResponseEntity<>(companyPageResponseDTO, HttpStatus.OK);
    }


    @GetMapping("/{copSeq}")
    public ResponseEntity<Object> findOne(@PathVariable Integer copSeq) {
        CompanyResponseDTO companyResponseDTO = companyService.findOne(copSeq);

        return new ResponseEntity<>(companyResponseDTO, HttpStatus.OK);
    }

    @PutMapping("/{copSeq}")
    public ResponseEntity<Object> updateCompany(@RequestBody CompanyDTO companyDTO, @PathVariable Integer copSeq) {
        CompanyResponseDTO companyResponseDTO = companyService.updateCompany(companyDTO, copSeq);

        return new ResponseEntity<>(companyResponseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{copSeq}")
    public ResponseEntity<Object> deleteCompany(@PathVariable Integer copSeq) {
        companyService.deleteCompany(copSeq);
        String message = "삭제 성공";

        return new ResponseEntity<>(message, HttpStatus.OK);
    }




}
