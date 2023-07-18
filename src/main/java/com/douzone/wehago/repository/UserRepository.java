package com.douzone.wehago.repository;

import com.douzone.wehago.domain.User;
import lombok.AllArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class UserRepository {

    private final SqlSession sqlSession;

    public void save(User user) {
        sqlSession.insert("com.douzone.wehago.mapper.UserMapper.userRegister", user);
    }

    public User findUser(String userId) {
        return sqlSession.selectOne("com.douzone.wehago.mapper.UserMapper.findUser", userId);
    }
}
