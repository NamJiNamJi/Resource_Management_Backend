package com.douzone.wehago.service;

import com.douzone.wehago.domain.User;
import com.douzone.wehago.dto.UserLoginDTO;
import com.douzone.wehago.dto.UserRegisterDTO;
import com.douzone.wehago.jwt.TokenDTO;
import com.douzone.wehago.jwt.TokenProvider;
import com.douzone.wehago.repository.UserRepository;
import com.douzone.wehago.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    // todo :: user 회원가입 service
    public void userRegister(UserRegisterDTO userRegisterDTO) {

        // todo :: 회원가입시 비밀번호, 비밀번호 확인 로직은 현재 구현되어있지 않음, 프론트 백 둘다하면 제일 좋지만 시간없다면 한곳에서만 진행해도 됨
        // 비밀번호 암호화
        String password = passwordEncoder.encode(userRegisterDTO.getUserPwd());
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        userRegisterDTO.beforeRegisterUpdate(password, timestamp, timestamp);
        User user = modelMapper.map(userRegisterDTO, User.class);
        userRepository.save(user);
    }

    // todo :: user 로그인 service
    public TokenDTO login(UserLoginDTO userLoginDTO) {

        String userId = userLoginDTO.getUserId();

        // 데이터베이스에 저장된 사용자 찾아오기
        User user = Optional.ofNullable(userRepository.findUser(userId))
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다."));

        if (!passwordEncoder.matches(userLoginDTO.getUserPwd(), user.getUserPwd())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // User 객체를 이용해 Authentication 객체 만들기
        UserDetailsImpl userDetails = new UserDetailsImpl(user);
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, "");

        TokenDTO tokenDTO = tokenProvider.generateTokenDto(authentication);

        // 토큰 리턴
        return tokenDTO;

    }
}
