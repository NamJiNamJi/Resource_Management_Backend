package com.douzone.wehago.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
public class UserRegisterDTO {

    private Long userSeq;
    @NotBlank
    private String userId;
    @NotBlank
    private String userPwd;

    private String userPwdConfirm;
    @NotBlank
    private String userName;
    @Email // pattern 으로 변경해서 써야할 수도..?
    private String userEmail;

    private Timestamp userCreated;
    private Timestamp userUpdated;

    public void beforeRegisterUpdate(String password, Timestamp userCreated, Timestamp userUpdated) {
        this.userPwd = password;
        this.userCreated = userCreated;
        this.userUpdated = userUpdated;
    }

}
