package com.douzone.wehago.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
public class DeviceSaveResponse {
    private String ok;
    private String message;
    private String fail;

}
