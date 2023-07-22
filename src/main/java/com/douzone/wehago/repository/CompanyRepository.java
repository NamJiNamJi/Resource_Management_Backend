package com.douzone.wehago.repository;

import com.douzone.wehago.domain.Company;
import lombok.AllArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class CompanyRepository {

    private final SqlSession sqlSession;

    public void save(Company company){
        sqlSession.insert("com.douzone.wehago.mapper.CompanyMapper.save", company);
    }
}
