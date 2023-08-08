package com.douzone.wehago.dto.car;

import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;
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
    private Timestamp carCreated;
    private Timestamp carUpdated;
}
