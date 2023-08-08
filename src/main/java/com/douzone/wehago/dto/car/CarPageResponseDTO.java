package com.douzone.wehago.dto.car;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class CarPageResponseDTO {
    List<CarResponseDTO> list;

}
