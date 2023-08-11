package com.douzone.wehago.mapper;

import com.douzone.wehago.domain.Reservation;
import com.douzone.wehago.dto.reservation.ResponseReservationDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReservationMapper {
    // test Mapper
    List<ResponseReservationDTO> findAllCarReservation(Integer copSeq);

    void registrationEvent(Reservation reservation);
}
