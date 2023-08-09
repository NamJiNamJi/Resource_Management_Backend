package com.douzone.wehago.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Employee {
    private Integer empSeq;
    private String empName;
    private String empPosition;
    private Integer copSeq;
    private Integer userSeq;
    private AuthLevel authLevel;
    private Boolean empState;
    private Timestamp empCreated;
    private Timestamp empUpdated;
}
