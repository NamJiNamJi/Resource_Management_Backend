package com.douzone.wehago.mapper;

import com.douzone.wehago.dto.reservation.ResponseReservationDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReservationMapper {

    List<ResponseReservationDTO> findAllCarReservation(Integer copSeq);
}
