package com.douzone.wehago.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class EmployeeResponseDTO {
    private Integer empSeq;
    private String empId;
    private String empName;
    private Integer copSeq;
    private Boolean rscAdmin;
}
