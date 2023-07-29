package com.douzone.wehago.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Builder
public class FacilityResponseDTO {

    private Integer carSeq;
    private String carName;
    private String carNumber;
    private String carDistance;
    private String carExplan;
    private LocalDate carYear;

}
