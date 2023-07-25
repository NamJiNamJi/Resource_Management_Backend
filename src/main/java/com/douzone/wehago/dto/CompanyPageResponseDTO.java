package com.douzone.wehago.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class CompanyPageResponseDTO {
    List<CompanyResponseDTO> companyList;
//    private int pageNum;
//    private int pageSize;

}
