package com.douzone.wehago.domain;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Builder
public class Company {
    private Integer copSeq;
    private String copRegNum;
    private String copName;
    private Boolean copState;
    private Timestamp copCreated;
    private Timestamp copUpdated;

}
