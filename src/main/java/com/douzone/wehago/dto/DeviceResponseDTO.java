package com.douzone.wehago.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class DeviceResponseDTO {
    private Integer dvcSeq;
    private String dvcName;
    private String dvcSerial;
    private String dvcImage;
    private LocalDate dvcBuy;
    private String dvcExplain;
    private Integer copSeq;
    private Integer rscSeq;
    private Boolean dvcState;
    private LocalDate dvcCreated;
    private LocalDate dvcUpdated;
}
