package com.douzone.wehago.mapper;

import com.douzone.wehago.domain.Facility;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FacilityMapper {

    /* 차량 자원 등록 Insert */
    void save (Facility facility);

    /* 차량 자원 전체 조회 Select */
    List<Facility> findAll();

    /* 선택 차량 조회 Select */
    Facility findOne (int car_number);

    /* 차량 자원 정보 수정 Update */
    void update (Facility facility);

    /* 차량 자원 정보 삭제 Delete */
    void delete (int car_number);
}
