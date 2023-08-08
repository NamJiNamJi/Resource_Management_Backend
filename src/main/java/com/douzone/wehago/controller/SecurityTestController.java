package com.douzone.wehago.controller;

import com.douzone.wehago.domain.CalendarContentTest;
import com.douzone.wehago.domain.SecurityCalendarContentTest;
import com.douzone.wehago.domain.User;
import com.douzone.wehago.security.PrincipalDetailsService;
import com.douzone.wehago.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
public class SecurityTestController {

    SecurityCalendarContentTest securityCalendarContentTest
            = SecurityCalendarContentTest.builder()
            .message("스프링에서 넘어온 메시지")
            .build();

    CalendarContentTest calendarContentTest = CalendarContentTest.builder()
            .title("테스트")
            .content(securityCalendarContentTest)
            .start("2023-07-11T13:00:00")
            .end("2023-07-12T15:00:00")
            .backgroundColor("green")
            .color("green")
            .build();

    CalendarContentTest calendarContentTest2 = CalendarContentTest.builder()
            .title("테스트")
            .content(securityCalendarContentTest)
            .start("2023-07-15T13:00:00")
            .end("2023-07-18T15:00:00")
            .backgroundColor("red")
            .color("red")
            .build();

    ArrayList<CalendarContentTest> arrayList = new ArrayList<>();


    // 캘린더 Mock 데이터 주고 받는 연결 테스트
    @GetMapping("/api/calendar/events")
    public ResponseEntity<?> getCalendarEvents(HttpServletRequest request) {
        return ResponseEntity.ok().body(calendarContentTest);
    }
}
