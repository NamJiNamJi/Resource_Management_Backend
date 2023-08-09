package com.douzone.wehago.mapper;

import com.douzone.wehago.domain.Car;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CarMapper {

    /* 차량 정보 저장 Insert */
    void save(Car car);

    /* 차량 전체 조회 Select */
    List<Car> findAll();

    /* 선택 차량 조회 Select */
    Car findOne(Integer car_seq);

    /* 차량 검색 Select */
    List<Car> searchCar(Map<String, Object> dataValues);

    /* 차량 정보 수정 Update */
    void update(Car car);

    /* 차량 정보 삭제 Delete */
    void delete(Car car);

//    /* 차량 정보 삭제 Delete */
//    void delete(Integer car_seq);

}
