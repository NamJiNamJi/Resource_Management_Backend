package com.douzone.wehago.domain;

import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long userSeq;
    private String userId;
    private String userPwd;
    private String userName;
    private String userEmail;
    private String userImage;
    private Timestamp userCreated;
    private Timestamp userUpdated;

}
