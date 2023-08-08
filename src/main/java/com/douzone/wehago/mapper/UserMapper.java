package com.douzone.wehago.mapper;

import com.douzone.wehago.domain.User;
import com.douzone.wehago.dto.user.UserDTO;
import com.douzone.wehago.dto.user.UserResponseDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    void userRegister(User user);
    User findUser(String userId);
    UserResponseDTO getUserData(Integer userSeq);


    List<User> findAllUsers();
    List<User> findSearchUsers(String text);
    int updateUser(UserDTO userDTO); // MyBatis update 행 갯수 return, 0 이면 update 가 되지 않은 것.
    int updateImage(UserDTO userDTO);

    int updatePwd(UserDTO userDTO);
    // 회원가입시 유저 Id 중복검사
    int duplicationUserId(String userId);
}
