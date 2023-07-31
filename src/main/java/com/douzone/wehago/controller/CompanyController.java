package com.douzone.wehago.controller;

import com.douzone.wehago.common.Response;
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

@RestController
@AllArgsConstructor
@RequestMapping("api/company")
public class CompanyController {
    private final CompanyService companyService;

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody CompanyDTO companyDTO) {
        CompanyResponseDTO companyResponseDTO = companyService.save(companyDTO);
        Response response = new Response(HttpStatus.CREATED, "회사 등록 성공", companyResponseDTO);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
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
        Response response = new Response(HttpStatus.OK, "회사 전체 조회 성공", companyPageResponseDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/{copSeq}")
    public ResponseEntity<Object> findOne(@PathVariable Integer copSeq) {
        CompanyResponseDTO companyResponseDTO = companyService.findOne(copSeq);
        Response response = new Response(HttpStatus.OK, "회사 조회 성공", companyResponseDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{copSeq}")
    public ResponseEntity<Object> updateCompany(@RequestBody CompanyDTO companyDTO, @PathVariable Integer copSeq) {
        CompanyResponseDTO companyResponseDTO = companyService.updateCompany(companyDTO, copSeq);
        Response response = new Response(HttpStatus.OK, "회사 수정 성공", companyResponseDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 삭제 로직(회사 상태 값만 변경)
    @PutMapping("/del/{copSeq}")
    public ResponseEntity<Object> deleteCompany(@RequestBody CompanyDTO companyDTO, @PathVariable Integer copSeq) {
        companyService.deleteCompany(companyDTO, copSeq);
        Response response = new Response(HttpStatus.OK, "회사 삭제 성공",null);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

//    @DeleteMapping("/{copSeq}")
//    public ResponseEntity<Object> deleteCompany(@PathVariable Integer copSeq) {
//        companyService.deleteCompany(copSeq);
//        Response response = new Response(HttpStatus.OK, "회사 삭제 성공",null);
//
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }




}
