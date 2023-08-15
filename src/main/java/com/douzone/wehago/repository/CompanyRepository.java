package com.douzone.wehago.repository;

import com.douzone.wehago.domain.Company;
import com.douzone.wehago.dto.company.CompanyDTO;
import lombok.AllArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class CompanyRepository {

    private final SqlSession sqlSession;

    public CompanyDTO save(Company company){
        return sqlSession.selectOne("com.douzone.wehago.mapper.CompanyMapper.save", company);
    }

    public List<Company> findAll(Boolean state) {
        return sqlSession.selectList("com.douzone.wehago.mapper.CompanyMapper.findAll", state);
    }

    public Company findOne(Integer copSeq){
        return sqlSession.selectOne("com.douzone.wehago.mapper.CompanyMapper.findOne", copSeq);
    }

    public Integer update(Company company){
        return sqlSession.update("com.douzone.wehago.mapper.CompanyMapper.update", company);
    }

    public void updateState(Company company){
        sqlSession.update("com.douzone.wehago.mapper.CompanyMapper.updateState", company);
    }

    public List<Company> findAllByCopSeq(Integer copSeq) {
        return sqlSession.selectList("com.douzone.wehago.mapper.CompanyMapper.findAllByCopSeq", copSeq);
    }
}
