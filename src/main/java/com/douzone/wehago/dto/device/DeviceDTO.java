package com.douzone.wehago.dto.device;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class DeviceDTO {
    private Integer dvcSeq;
    private String dvcName;
    private String dvcSerial;
    private String dvcImage;
    private LocalDate dvcBuy;
    private String dvcExplain;
    private Integer copSeq;
    private Integer rscSeq;
}