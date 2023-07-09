package com.douzone.wehago.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Getter
@Setter
@ToString
public class Room {
    private String lender;
    private Integer roomNumber;
    private Integer participant;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate reserveDate;
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime startTime;
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime endTime;
}
