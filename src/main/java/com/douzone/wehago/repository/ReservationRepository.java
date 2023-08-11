package com.douzone.wehago.repository;

import com.douzone.wehago.domain.Reservation;
import lombok.AllArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class ReservationRepository {

    private final SqlSession sqlSession;

    public void registrationEvent(Reservation reservation) {
        sqlSession.insert("com.douzone.wehago.mapper.ReservationMapper.registrationEvent", reservation);
    }
}
