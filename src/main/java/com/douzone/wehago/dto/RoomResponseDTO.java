package com.douzone.wehago.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class RoomResponseDTO {
    private Integer spcSeq;
    private String spcName;
    private String spcCap;
    private String spcExplian;
    private String spcImage;
    private Integer copSeq;
    private Integer rscSeq;
    private Boolean spcState;
    private LocalDate spcCreated;
    private LocalDate spcUpdated;
}
