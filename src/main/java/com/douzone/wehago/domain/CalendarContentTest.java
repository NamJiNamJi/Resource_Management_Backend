package com.douzone.wehago.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class CalendarContentTest {

    private String title;
    private SecurityCalendarContentTest content;
    private String start;
    private String end;
    private String backgroundColor;
    private String color;

}
