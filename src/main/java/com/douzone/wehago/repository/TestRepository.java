package com.douzone.wehago.repository;

import com.douzone.wehago.domain.Test;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TestRepository {

    private final SqlSession sqlSession;

    public void save(Test test){
        sqlSession.insert("com.douzone.wehago.mapper.TestMapper.save", test);
    }

}
