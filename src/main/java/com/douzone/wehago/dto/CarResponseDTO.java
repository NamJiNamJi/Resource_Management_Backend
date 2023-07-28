package com.douzone.wehago.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class CarResponseDTO {
    private Integer carSeq;
    private String carName;
    private String carNumber;
    private String carDistance;
    private LocalDate carYear;
    private String carImage;
    private String carExplain;
    private Integer copSeq;
    private Integer rscSeq;
    private Boolean carState;
    private LocalDate carCreated;
    private LocalDate carUpdated;
}
