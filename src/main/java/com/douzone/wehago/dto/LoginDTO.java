package com.douzone.wehago.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class LoginDTO {
    @NotBlank
    private String userId;
    @NotBlank
    private String password;
}
