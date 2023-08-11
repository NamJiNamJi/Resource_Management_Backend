package com.douzone.wehago.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Reservation {

    private Integer copSeq;
    private Integer rsvDetail;
    private String rsvId;
    private String rsvName;
    private String rsvTitle;
    private Integer rsvParti;
    private String rsvExplain;
    private Timestamp rsvStart;
    private Timestamp rsvEnd;

}
