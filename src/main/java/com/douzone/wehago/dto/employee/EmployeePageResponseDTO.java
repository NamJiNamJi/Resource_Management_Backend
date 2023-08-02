package com.douzone.wehago.dto.employee;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class EmployeePageResponseDTO {
    List<EmployeeResponseDTO> employeeList;
}
