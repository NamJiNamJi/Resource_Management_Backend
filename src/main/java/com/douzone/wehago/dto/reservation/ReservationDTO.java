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
@Setter
@Getter
public class ReservationDTO {
    private Timestamp rsvStart; // 예약 시작 시간
    private Timestamp rsvEnd; // 예약 종료 시간
    private Integer copSeq;
}
