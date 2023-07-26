package com.douzone.wehago.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Employee {
    private Integer empSeq;
    private String empId;
    private String empName;
    private Integer copSeq;
    private Boolean rscAdmin;
}
