package com.douzone.wehago.dto.employee;

import com.douzone.wehago.domain.AuthLevel;
import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;

@Builder
@Getter
public class EmployeeResponseDTO {
    private Integer empSeq;
    private String empName;
    private String empPosition;
    private String empImage;
    private Integer copSeq;
    private Integer userSeq;
    private AuthLevel authLevel;
    private Boolean empState;
    private Timestamp empCreated;
    private Timestamp empUpdated;
}
