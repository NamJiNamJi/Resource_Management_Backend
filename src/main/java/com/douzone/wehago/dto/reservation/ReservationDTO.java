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

    private Integer rsvSeq; // 예약 일련번호
    private Integer copSeq; // 회사 일련번호
    private Integer rsvDetail; //세부 일련번호
    private String rsvId; // 예약자 아이디
    private String rsvName; // 예약자명
    private String rsvExplain; // 설명
    private String rsvParti; // 사용인원
    private String rsvTitle; // 예약제목
    private Timestamp rsvStart; // 예약 시작 시간
    private Timestamp rsvEnd; // 예약 종료 시간
    private Timestamp rsvCreated;
    private Timestamp rsvUpdated;
    private boolean rsvState; // 예약 상태

}
