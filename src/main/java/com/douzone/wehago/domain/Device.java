package com.douzone.wehago.domain;

import lombok.*;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.awt.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Device {
    private Integer dvcSeq;
    private String dvcName;
    private String dvcSerial;
    private String dvcImage;
    private LocalDate dvcBuy;
    private String Explain;
    private Integer copSeq;
    private Integer rscSeq;
    private Boolean dvcState;
    private LocalDate dvcCreated;
    private LocalDate dvcUpdated;
}