package com.douzone.wehago.domain;

import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Car {
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

