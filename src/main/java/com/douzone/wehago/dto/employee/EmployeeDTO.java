package com.douzone.wehago.dto.employee;

import com.douzone.wehago.domain.AuthLevel;
import lombok.Getter;

@Getter
public class EmployeeDTO {
    private String empName;
    private Integer copSeq;
    private Integer userSeq;
    private Boolean empState;
    private AuthLevel authLevel;

}
