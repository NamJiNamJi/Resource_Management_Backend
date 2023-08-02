package com.douzone.wehago.repository;

import com.douzone.wehago.domain.Space;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class SpaceRepository {

    private final SqlSession sqlSession;

    public void save(Space space) {
        sqlSession.insert("com.douzone.wehago.mapper.SpaceMapper.save", space);
    }

    public List<Space> findAll() {
        return sqlSession.selectList("com.douzone.wehago.mapper.SpaceMapper.findAll");
    }

    public Space findOne (Integer spc_seq) {
        return sqlSession.selectOne("com.douzone.wehago.mapper.SpaceMapper.findOne", spc_seq);
    }

    public Integer update (Space space) {
        return  sqlSession.update("com.douzone.wehago.mapper.SpaceMapper.update", space);
    }

    public void delete (Integer spc_seq) {
        sqlSession.delete("com.douzone.wehago.mapper.SpaceMapper.delete", spc_seq);
    }
}
