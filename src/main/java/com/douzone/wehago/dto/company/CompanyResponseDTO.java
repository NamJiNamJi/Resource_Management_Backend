package com.douzone.wehago.dto.company;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CompanyResponseDTO {
    private Integer copSeq;
    private String copRegNum;
    private String copName;

}
