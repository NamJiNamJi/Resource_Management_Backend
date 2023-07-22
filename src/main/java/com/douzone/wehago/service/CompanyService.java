package com.douzone.wehago.service;

import com.douzone.wehago.domain.Company;
import com.douzone.wehago.dto.CompanyDTO;
import com.douzone.wehago.dto.CompanyResponseDTO;
import com.douzone.wehago.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        CompanyResponseDTO companyResponseDTO = CompanyResponseDTO.builder()
                .copSeq(company.getCopSeq())
                .copRegNum(company.getCopRegNum())
                .copName(company.getCopName())
                .copAdmin(company.getCopAdmin())
                .build();
        return companyResponseDTO;
    }
}
