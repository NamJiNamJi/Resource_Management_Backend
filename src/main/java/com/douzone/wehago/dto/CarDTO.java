package com.douzone.wehago.dto;

import lombok.Getter;
import java.time.LocalDate;

@Getter
public class CarDTO {
    private String carName;
    private String carNumber;
    private String carDistance;
    private LocalDate carYear;
    private String carExplain;
    private Integer copSeq;
    private Integer rscSeq;
}
