package com.douzone.wehago.dto.employee;

import com.douzone.wehago.domain.AuthLevel;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class EmployeeResponseDTO {
    private Integer empSeq;
    private String empName;
    private String empImage;
    private Integer copSeq;
    private Integer userSeq;
    private Boolean empState;
    private AuthLevel authLevel;
}
