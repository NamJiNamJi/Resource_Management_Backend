package com.douzone.wehago.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.awt.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;


@ToString
@Getter
@Setter
public class Device {
    private Long dvcSeq;            // 전자기기 일련번호
    private String dvcName;         // 전자기기 명
    private String dvcSerial;       // 전자기기 시리얼번호
    private byte[] dvcImage;        // 전자기기 이미지
    private Date dvcBuy;            // 전자기기 구입년도
    private String dvcExplan;       // 전자기기 설명
    private Timestamp dvcCreated;   // 생성일시
    private Timestamp dvcUpdated;   // 수정일시
}
