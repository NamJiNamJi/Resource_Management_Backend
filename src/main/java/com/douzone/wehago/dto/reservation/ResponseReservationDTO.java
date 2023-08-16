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
    private Integer rsvDetail; //세부 일련번호
    private String rsvId; // 예약자 아이디
    private String rsvName; // 예약자명
    private String rsvExplain; // 설명
    private Integer rsvParti; // 사용인원
    private String rsvTitle; // 예약제목
    private Integer rsvNum;
    private Timestamp rsvStart; // 예약 시작 시간
    private Timestamp rsvEnd; // 예약 종료 시간
    private Timestamp rsvCreated;
    private Timestamp rsvUpdated;
    private boolean rsvState; // 예약 상태
<<<<<<< HEAD
=======

>>>>>>> 5333b2057f59a0a4ae102e4e1803838f7192b7d8
}
