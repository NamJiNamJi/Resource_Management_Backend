package com.douzone.wehago.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    private Long userSeq;
    private String userId;
    private String userPwd;
    private String userName;
    private String userEmail;
    private LocalDateTime userCreated;
    private LocalDateTime userUpdated;
  
}
