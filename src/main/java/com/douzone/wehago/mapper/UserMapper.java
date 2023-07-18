package com.douzone.wehago.mapper;

import com.douzone.wehago.domain.User;
import com.douzone.wehago.dto.UserLoginDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface UserMapper {
    void userRegister(User user);
    User findUser(String userId);
}
