package com.douzone.wehago.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberLoginResponse {
    private String ok;
    private String message;
}
