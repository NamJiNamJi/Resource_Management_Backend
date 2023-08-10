package com.douzone.wehago.dto.reservation;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ReservationPageResponseDTO {
    List<ResponseReservationDTO> list;
    private Integer pageNum;
    private Integer pageSize;
    private Object total;
}
