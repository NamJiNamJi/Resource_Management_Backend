package com.douzone.wehago.dto.reservation;

import lombok.*;

import java.sql.Timestamp;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ResponseReservationDTO {

    private Integer rsvSeq; // 예약 일련번호
    private Integer copSeq; // 회사 일련번호
    private Integer rscSeq; // 자원 일련번호
    private String rscName; // 자원명 ex) 차량, 모바일기기, 공간
    private Integer rsvDetail; // 세부 일련번호
    private String rsvDetailName; // 세부 자원명
    private String rsvId; // 예약자 아이디
    private String rsvName; // 예약자명
    private String rsvExplain; // 설명
    private Timestamp rsvStart; // 예약 시작 시간
    private Timestamp rsvEnd; // 예약 종료 시간
}
