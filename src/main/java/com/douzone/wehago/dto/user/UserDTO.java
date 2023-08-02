package com.douzone.wehago.dto.user;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserDTO {

    private Integer copSeq;
    private String copRegNum;
    private String copName;
    private String userSeq;
    private String userId;
    private String userName;
    private String userEmail;
    private String userImage;
    private String empPosition;
    private String empImage;
    private String authLevel;
    
}
