package com.douzone.wehago.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Employee {
    private Integer empSeq;
    private String empName;
    private String empImage;
    private Integer copSeq;
    private Integer userSeq;
    private AuthLevel authLevel;
    private Boolean empState;
}
