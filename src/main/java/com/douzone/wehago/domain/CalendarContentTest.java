package com.douzone.wehago.domain;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class CalendarContentTest {

    private String title;
    private SecurityCalendarContentTest content;
    private String start;
    private String end;
    private String backgroundColor;
    private String color;
    private LocalDate calendar;
}
