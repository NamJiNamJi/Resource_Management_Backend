package com.douzone.wehago.service;

import com.douzone.wehago.domain.Company;
import com.douzone.wehago.domain.Member;
import com.douzone.wehago.dto.CompanyDTO;
import com.douzone.wehago.dto.CompanyPageResponseDTO;
import com.douzone.wehago.dto.CompanyResponseDTO;
import com.douzone.wehago.repository.CompanyRepository;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Log4j2
@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;

    @Transactional
    public CompanyResponseDTO save(CompanyDTO companyDTO) {
        Company company = Company.builder()
                .copRegNum(companyDTO.getCopRegNum())
                .copName(companyDTO.getCopName())
                .copAdmin(companyDTO.getCopAdmin())
                .build();
        companyRepository.save(company);

        return getCompanyResponseDTO(company);
    }

//    @Transactional(readOnly = true)
//    public CompanyResponseDTO findAll(int pageNum, int pageSize) {
//    public void findAll(int pageNum, int pageSize) {
//
//        PageHelper.startPage(pageNum, pageSize);
//
//        List<Company> companyList = companyRepository.findAll(pageNum, pageSize);
//        PageInfo.of(companyList);
//        log.info(PageInfo.of(companyList));

//        CompanyResponseDTO companyResponseDTO = CompanyResponseDTO.builder()

//    }

    @Transactional(readOnly = true)
    public CompanyPageResponseDTO findAll() {
        List<Company> companyList = companyRepository.findAll();
        System.out.println(companyList.toString());
        System.out.println(companyList.get(1));

        List<CompanyResponseDTO> companyResponseDTOList = new ArrayList<>();

        for(Company company : companyList) {
            companyResponseDTOList.add(getCompanyResponseDTO(company));
        }

        return CompanyPageResponseDTO.builder()
                .companyList(companyResponseDTOList)
                .build();
    }

    @Transactional(readOnly = true)
    public CompanyResponseDTO findOne(Integer copSeq) {
        Company company = companyRepository.findOne(copSeq);
        System.out.println("단일조회" + company);


        return getCompanyResponseDTO(company);
    }

    // 회사 수정
    @Transactional
    public CompanyResponseDTO updateCompany(CompanyDTO companyDTO, Integer copSeq) {
        Company company = Company.builder()
                .copSeq(copSeq)
                .copRegNum(companyDTO.getCopRegNum())
                .copName(companyDTO.getCopName())
                .copAdmin(companyDTO.getCopAdmin())
                .build();

        companyRepository.update(company);

        return getCompanyResponseDTO(company);
    }




    private CompanyResponseDTO getCompanyResponseDTO(Company company) {

        return CompanyResponseDTO.builder()
                .copSeq(company.getCopSeq())
                .copRegNum(company.getCopRegNum())
                .copName(company.getCopName())
                .copAdmin(company.getCopAdmin())
                .build();
    }



}
