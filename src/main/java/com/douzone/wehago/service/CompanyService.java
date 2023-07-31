package com.douzone.wehago.service;

import com.douzone.wehago.domain.Company;
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

import java.sql.Timestamp;
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
                .copState(companyDTO.getCopState())
                .build();
        companyRepository.save(company);

//        company = companyRepository.findOne(company.getCopSeq());

        return getCompanyResponseDTO(company);
    }

    @Transactional(readOnly = true)
    public CompanyPageResponseDTO findAll() {
        // 회사 상태가 true인 값만 조회
        Boolean state = true;
        List<Company> companyList = companyRepository.findAll(state);
//        System.out.println(companyList.toString());
//        System.out.println(companyList.get(1));

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

        return getCompanyResponseDTO(company);
    }

    // 회사 수정
    @Transactional
    public CompanyResponseDTO updateCompany(CompanyDTO companyDTO, Integer copSeq) {
        Company company = Company.builder()
                .copSeq(copSeq)
                .copRegNum(companyDTO.getCopRegNum())
                .copName(companyDTO.getCopName())
                .copState(companyDTO.getCopState())
                .copUpdated(new Timestamp(System.currentTimeMillis()))
                .build();

        companyRepository.update(company);

        return getCompanyResponseDTO(company);
    }

    // 회사 삭제
    @Transactional
    public CompanyResponseDTO deleteCompany(CompanyDTO companyDTO, Integer copSeq) {
        Company company = Company.builder()
                .copSeq(copSeq)
                .copRegNum(companyDTO.getCopRegNum())
                .copName(companyDTO.getCopName())
                .copState(companyDTO.getCopState())
                .copUpdated(new Timestamp(System.currentTimeMillis()))
                .build();

        companyRepository.updateState(company);

        return getCompanyResponseDTO(company);
    }





    // 회사 삭제
//    @Transactional
//    public void deleteCompany(Integer copSeq) {
//        companyRepository.delete(copSeq);
//    }




    private CompanyResponseDTO getCompanyResponseDTO(Company company) {

        return CompanyResponseDTO.builder()
                .copSeq(company.getCopSeq())
                .copRegNum(company.getCopRegNum())
                .copName(company.getCopName())
                .copState(company.getCopState())
                .build();
    }



}
