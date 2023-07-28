package com.douzone.wehago.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class DevicePageResponseDTO {
    List<DeviceResponseDTO> dvcList;
}
