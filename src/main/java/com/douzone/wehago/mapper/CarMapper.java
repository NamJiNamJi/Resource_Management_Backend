package com.douzone.wehago.mapper;

import com.douzone.wehago.domain.Car;
import com.douzone.wehago.domain.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CarMapper {

    void save(Car Car);

    List<Car> findAll();

//    Member findOne(String memberId);

//    void update(Member member);

//    void delete(String memberId);
}
