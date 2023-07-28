package com.douzone.wehago.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
public class FacilityPageResponseDTO {
    List<FacilityResponseDTO> facilityList;
}
