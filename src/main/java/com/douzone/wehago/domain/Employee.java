package com.douzone.wehago.domain;

import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;

@Builder
@Getter
public class Employee {
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
