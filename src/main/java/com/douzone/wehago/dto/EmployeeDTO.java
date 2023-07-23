package com.douzone.wehago.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class EmployeeDTO {
    private String empId;
    private String empName;
    private Integer copSeq;
    private Boolean rscAdmin;
}
