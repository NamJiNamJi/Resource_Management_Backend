package com.douzone.wehago.repository;

import com.douzone.wehago.domain.Car;
import com.douzone.wehago.domain.Member;
import com.douzone.wehago.domain.Room;
import lombok.AllArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class RoomRepository {

    private final SqlSession sqlSession;
    public void save(Room room){
        sqlSession.insert("com.douzone.wehago.mapper.RoomMapper.save", room);
    }
    public List<Member> findAll(){
        return sqlSession.selectList("com.douzone.wehago.mapper.RoomMapper.findAll");
    }

//    public Member findOne(String memberId){
//        return sqlSession.selectOne("com.douzone.wehago.mapper.MemberMapper.findOne", memberId);
//    }
//
//    public Integer update(Member member){
//        return sqlSession.update("com.douzone.wehago.mapper.MemberMapper.update", member);
//    }
//
//    public void delete(String memberId){
//        sqlSession.delete("com.douzone.wehago.mapper.MemberMapper.delete", memberId);
//    }
}
