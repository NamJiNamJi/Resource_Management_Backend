package com.douzone.wehago.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MailDTO {

    private String empMail;
    private String empName;
    private String empPosition;
    private Integer userSeq;
    private String authLevel;
}
