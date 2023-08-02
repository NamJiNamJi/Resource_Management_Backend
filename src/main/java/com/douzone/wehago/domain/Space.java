package com.douzone.wehago.domain;

import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Space {
    private Integer spcSeq;
    private String spcName;
    private String spcCap;
    private String spcExplain;
    private String spcImage;
    private Integer copSeq;
    private Integer rscSeq;
    private Boolean carState;
    private LocalDate carCreated;
    private LocalDate carUpdated;

}
