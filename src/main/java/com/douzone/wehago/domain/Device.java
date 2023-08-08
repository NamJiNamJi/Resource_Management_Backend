package com.douzone.wehago.domain;

import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Device {
    private Integer dvcSeq;
    private String dvcName;
    private String dvcSerial;
    private String dvcImage;
    private LocalDate dvcBuy;
    private String dvcExplain;
    private Integer copSeq;
    private Integer rscSeq;
    private Boolean dvcState;
    private Timestamp dvcCreated;
    private Timestamp dvcUpdated;
}