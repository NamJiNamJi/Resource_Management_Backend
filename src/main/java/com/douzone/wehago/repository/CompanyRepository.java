package com.douzone.wehago.repository;

import com.douzone.wehago.domain.Company;
import com.douzone.wehago.domain.Member;
import lombok.AllArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class CompanyRepository {

    private final SqlSession sqlSession;

    public void save(Company company){
        sqlSession.insert("com.douzone.wehago.mapper.CompanyMapper.save", company);
    }

    public List<Company> findAll() {
        return sqlSession.selectList("com.douzone.wehago.mapper.CompanyMapper.findAll");
    }

    public Company findOne(Integer copSeq){
        return sqlSession.selectOne("com.douzone.wehago.mapper.CompanyMapper.findOne", copSeq);
    }

    public Integer update(Company company){
        return sqlSession.update("com.douzone.wehago.mapper.CompanyMapper.update", company);
    }

}
