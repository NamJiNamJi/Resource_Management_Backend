package com.douzone.wehago.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CompanyResponseDTO {
    private Integer copSeq;
    private String copRegNum;
    private String copName;
    private String copAdmin;

}
