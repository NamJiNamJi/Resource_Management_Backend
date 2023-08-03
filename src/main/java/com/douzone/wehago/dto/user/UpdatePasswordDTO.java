package com.douzone.wehago.dto.user;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UpdatePasswordDTO {

    @NotBlank
    private Integer userSeq;
    @NotBlank
    private String userPwd;
}
