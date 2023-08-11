package com.douzone.wehago.repository;

import com.douzone.wehago.domain.Reservation;
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
    public void registrationEvent(Reservation reservation) {
        sqlSession.insert("com.douzone.wehago.mapper.ReservationMapper.registrationEvent", reservation);
    }
}
