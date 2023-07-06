package com.douzone.wehago.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
public class Member {
    private String memberId;
    private String memberName;
    private String password;
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    private LocalDate dateOfBirth;
}
