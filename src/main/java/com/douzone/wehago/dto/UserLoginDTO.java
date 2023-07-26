package com.douzone.wehago.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserLoginDTO {

    @NotBlank
    private String userId;
    @NotBlank
    private String userPwd;

}
