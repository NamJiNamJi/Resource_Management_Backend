package com.douzone.wehago.dto.space;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class SpacePageResponseDTO {
    List<SpaceResponseDTO> list;
}
