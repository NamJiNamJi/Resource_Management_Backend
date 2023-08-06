package com.douzone.wehago.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MailDTO {

    private String empEmail;
    private String empName;
    private String empPosition;
    private Integer copSeq;
    private Integer userSeq;
    private String authLevel;
}
