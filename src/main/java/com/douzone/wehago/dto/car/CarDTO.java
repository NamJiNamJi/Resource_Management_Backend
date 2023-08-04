package com.douzone.wehago.dto.car;

import lombok.Getter;
import java.time.LocalDate;

@Getter
public class CarDTO {
    private Integer carSeq;
    private String carName;
    private String carNumber;
    private String carDistance;
    private LocalDate carYear;
    private String carImage;
    private String carExplain;
    private Integer copSeq;
    private Integer rscSeq;
}
