package com.douzone.wehago.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
public class RoomDTO {
    private Long spcSeq;            // 공간 일련번호
    private String spcName;         // 공간 명
//  private byte[] spcImage;        // 공간 이미지
    private Integer spcCap;         // 공간 수용인원
    private String spcExplan;       // 공간 설명
    private Timestamp spcCreated;   // 생성일시
    private Timestamp spcUpdated;   // 수정일시
}
