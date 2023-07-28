package com.douzone.wehago.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserDTO {

    private String userId;
    private String userPwd;
    private String userName;
    private String userEmail;
    private String userImage;
}
