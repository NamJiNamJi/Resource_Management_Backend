package com.douzone.wehago.dto.space;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class SpaceResponseDTO {
    private Integer spcSeq;
    private String spcName;
    private String spcCap;
    private String spcImage;
    private String spcExplain;
    private Integer copSeq;
    private Integer rscSeq;
    private Boolean carState;
    private LocalDate carCreated;
    private LocalDate carUpdated;
}
