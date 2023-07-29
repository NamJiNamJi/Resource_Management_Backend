package com.douzone.wehago.repository;

import com.douzone.wehago.domain.User;
import com.douzone.wehago.dto.UserDTO;
import com.douzone.wehago.dto.UserLoginDTO;
import lombok.AllArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    public List<User> findAllUsers() {
        return sqlSession.selectList("com.douzone.wehago.mapper.UserMapper.findAllUsers");
    }

    public List<User> findSearchUsers(String text) {
        return sqlSession.selectList("com.douzone.wehago.mapper.UserMapper.findSearchUsers", text);
    }
    public int updatePwd(User user){
        System.out.println("UserRepos "+ user.getUserId());
        System.out.println("UserRepos "+ user.getUserPwd());

        return sqlSession.update("com.douzone.wehago.mapper.UserMapper.updatePwd", user);
    }
    public int updateUser(UserDTO userDTO) {
        return sqlSession.update("com.douzone.wehago.mapper.UserMapper.updateUser", userDTO);
    }
    public int updateImage(User user){
        return  sqlSession.update("com.douzone.wehago.mapper.UserMapper.updateImage", user);
    }
    public int duplicationUserId(String userId) {
        return sqlSession.selectOne("com.douzone.wehago.mapper.UserMapper.duplicationUserId", userId);
    }
}
