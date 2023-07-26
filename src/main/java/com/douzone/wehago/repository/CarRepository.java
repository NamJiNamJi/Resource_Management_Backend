package com.douzone.wehago.repository;

import com.douzone.wehago.domain.Car;
import com.douzone.wehago.domain.Member;
import com.douzone.wehago.mapper.MemberMapper;
import lombok.AllArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import java.math.BigInteger;
import java.util.List;

@Repository
@AllArgsConstructor
public class CarRepository {

    private final SqlSession sqlSession;
  
    public void save(Car car){
        sqlSession.insert("com.douzone.wehago.mapper.carMapper.save", car);
    }

    public List<Car> findAll(){
        return sqlSession.selectList("com.douzone.wehago.mapper.carMapper.findAll");
    }

    public Car findOne(int car_seq){
        return sqlSession.selectOne("com.douzone.wehago.mapper.MemberMapper.findOne", car_seq);
    }

    public int update(Car car){
        return sqlSession.update("com.douzone.wehago.mapper.MemberMapper.update", car);
    }

    public void delete(int car_seq){
        sqlSession.delete("com.douzone.wehago.mapper.MemberMapper.delete", car_seq);
    }

}
