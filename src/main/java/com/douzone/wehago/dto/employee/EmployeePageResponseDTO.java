package com.douzone.wehago.dto.employee;

import com.github.pagehelper.Page;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class EmployeePageResponseDTO {
    List<EmployeeResponseDTO> employeeList;
    private Integer pageNum;
    private Integer pageSize;
    private Object total;
}
