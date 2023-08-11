package com.douzone.wehago.dto.reservation;
import lombok.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReservationDTO {

    private Integer copSeq;
    private Integer rsvDetail;
    private String rsvId;
    private String rsvName;
    private String rsvTitle;
    private Integer rsvParti;
    private String rsvExplain;
    private Timestamp rsvStart;
    private Timestamp rsvEnd;

//    private Timestamp rsvStart; // 예약 시작 시간
//    private Timestamp rsvEnd; // 예약 종료 시간
//    private Integer copSeq;
}
