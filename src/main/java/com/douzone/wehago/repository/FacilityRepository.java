package com.douzone.wehago.repository;

import com.douzone.wehago.domain.Car;
import com.douzone.wehago.domain.Facility;
import lombok.AllArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class FacilityRepository {

    private final SqlSession sqlSession;
    public void save(Facility facility){
        sqlSession.insert("com.douzone.wehago.mapper.FacilityMapper.save", facility);
    }

    public List<Facility> findAll(){
        return sqlSession.selectList("com.douzone.wehago.mapper.FacilityMapper.findAll");
    }

    public Facility findOne(int carNumber){
        return sqlSession.selectOne("com.douzone.wehago.mapper.FacilityMapper.findOne", carNumber);
    }

    public int update(Facility facility){
        return sqlSession.update("com.douzone.wehago.mapper.FacilityMapper.update", facility);
    }

    public void delete(int carNumber){
        sqlSession.delete("com.douzone.wehago.mapper.FacilityMapper.delete", carNumber);
    }
}
