package com.douzone.wehago.dto.employee;

import com.douzone.wehago.domain.AuthLevel;
import lombok.Getter;

@Getter
public class EmployeeDTO {
    private String empName;
    private String empPosition;
    private AuthLevel authLevel;
}
