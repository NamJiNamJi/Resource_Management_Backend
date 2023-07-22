package com.douzone.wehago.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Company {
    private Integer copSeq;
    private String copRegNum;
    private String copName;
    private String copAdmin;
}
