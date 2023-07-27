package com.douzone.wehago.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDTO {
    private String copRegNum;
    private String copName;
    private Boolean copState;
}
