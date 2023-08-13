package com.douzone.wehago.repository;

import com.douzone.wehago.domain.Reservation;
import com.douzone.wehago.dto.reservation.AvailableReservationDTO;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;
@RequiredArgsConstructor
@Repository
public class ReservationRepository {

    private final SqlSession sqlSession;

    public List<Reservation> reservationList(Integer pageNo, Integer pageSize,String rsvId) {
        PageHelper.startPage(pageNo, pageSize);
        return sqlSession.selectList("com.douzone.wehago.mapper.ReservationMapper.reservationList", rsvId);
    }


    public void updateReservation() {
        sqlSession.update("com.douzone.wehago.mapper.ReservationMapper.updateReservation");
    }


    // 이벤트 등록
    public void registrationEvent(Reservation reservation) {
        sqlSession.insert("com.douzone.wehago.mapper.ReservationMapper.registrationEvent", reservation);

    }

    // 현재 true 값인 모든 이벤트 찾기
    public List<AvailableReservationDTO> findAllAvailableReservation(Integer copSeq) {
        return sqlSession.selectList("com.douzone.wehago.mapper.ReservationMapper.findAllAvailableReservation", copSeq);
    }
}
