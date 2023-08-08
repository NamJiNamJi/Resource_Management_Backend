package com.douzone.wehago.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    private Integer spcSeq;
    private String spcName;
    private String spcCap;
    private String spcExplain;
    private String spcImage;
    private Integer copSeq;
    private Integer rscSeq;
    private Boolean spcState;
    private LocalDate spcCreated;
    private LocalDate spcUpdated;
}
