package com.douzone.wehago.service;

import com.douzone.wehago.common.Response;
import com.douzone.wehago.common.S3Uploader;
import com.douzone.wehago.domain.User;
import com.douzone.wehago.dto.user.*;
import com.douzone.wehago.jwt.TokenDTO;
import com.douzone.wehago.jwt.TokenProvider;
import com.douzone.wehago.repository.UserRepository;
import com.douzone.wehago.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final S3Uploader s3Uploader;

    // todo :: user 회원가입 service
    public Response userRegister(UserRegisterDTO userRegisterDTO) {
        // todo :: 회원가입시 비밀번호, 비밀번호 확인 로직은 현재 구현되어있지 않음, 프론트 백 둘다하면 제일 좋지만 시간없다면 한곳에서만 진행해도 됨
        // 비밀번호 암호화
        String password = passwordEncoder.encode(userRegisterDTO.getUserPwd());
        userRegisterDTO.beforeRegisterUpdate(password);
        User user = modelMapper.map(userRegisterDTO, User.class);
        userRepository.save(user);

        return new Response(HttpStatus.OK, "회원가입을 축하합니다!", null);
    }

    // todo :: 비밀번호 변경
    public Response updatePwd(UpdatePasswordDTO updatePasswordDTO) {

        String password = passwordEncoder.encode(updatePasswordDTO.getUserPwd());

        updatePasswordDTO.setUserPwd(password);

        int result = userRepository.updatePwd(updatePasswordDTO);

        if (result == 1) {
            return new Response(HttpStatus.OK, "비밀번호가 변경되었습니다.", null);
        }

        return new Response(HttpStatus.BAD_REQUEST, "비밀번호 변경 실패", null);

    }

    public Response userPwd(UserLoginDTO userLoginDTO) {

        String userId = userLoginDTO.getUserId();

        try {
            User user = Optional.ofNullable(userRepository.findUser(userId))
                    .orElseThrow(() -> new IllegalArgumentException("아이디를 다시 한번 확인해주세요."));
          if (!passwordEncoder.matches(userLoginDTO.getUserPwd(), user.getUserPwd())) {
                throw new IllegalArgumentException("아이디나 비밀번호를 다시 한번 확인해주세요.");
            }

          return new Response(HttpStatus.OK, "", null);

        } catch (IllegalArgumentException le) {
            log.info("아이디나 비밀번호가 일치하지 않습니다.");
            return new Response(HttpStatus.BAD_REQUEST, "", null);
        }
    }

    // todo :: user 회원가입 유저 Id 중복검사
    public int userIdCheck(String userId) {
        return userRepository.duplicationUserId(userId);
    }


    // todo :: user 로그인 service
    public Response userLogin(UserLoginDTO userLoginDTO) {

        String userId = userLoginDTO.getUserId();

        // todo :: 나중에 하늘이가 custom Exception 을 만들어오면 오류 코드 수정
        // 데이터베이스에 저장된 사용자 찾아오기
        try {
            User user = Optional.ofNullable(userRepository.findUser(userId))
                    .orElseThrow(() -> new IllegalArgumentException("아이디를 다시 한번 확인해주세요."));
            if (!passwordEncoder.matches(userLoginDTO.getUserPwd(), user.getUserPwd())) {
                throw new IllegalArgumentException("아이디나 비밀번호를 다시 한번 확인해주세요.");
            }

            // User 객체를 이용해 Authentication 객체 만들기
            UserDetailsImpl userDetails = new UserDetailsImpl(user);
            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, "");

            TokenDTO tokenDTO = tokenProvider.generateTokenDto(authentication);

            System.out.println(user.getUserSeq());

            UserDTO userDTO = userRepository.findUserData(user.getUserSeq());

            UserResponseDTO userResponseDTO = UserResponseDTO.builder()
                    .tokenDTO(tokenDTO)
                    .userDTO(userDTO)
                    .build();

            return Response.builder()
                    .status(HttpStatus.OK)
                    .message("로그인 성공")
                    .data(userResponseDTO)
                    .build();

            // todo :: 나중에 catch error 도 분기처리해야 할 수도 있음.
        } catch (IllegalArgumentException le) {
            log.info("아이디나 비밀번호가 일치하지 않습니다.");
            return Response.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .message("로그인 실패")
                    .build();
        }
    }

//    // todo :: 전체 유저찾기 (전체 사원찾기 아니에요)... 지금은 그냥 요청해도 통과되지만 나중에는 accessToken 값 던져주셔야 되요.
//    public List<UserDTO> findAllUsers() {
//         List<User> allUsers = userRepository.findAllUsers();
//         return allUsers.stream()
//                .map(user -> modelMapper.map(user, UserDTO.class))
//                .collect(Collectors.toList());
//    }
//
//    // todo :: 특정 유저 찾기 .. 지금은 그냥 요청해도 통과되지만 나중에는 accessToken 값 던져주셔야 되요.
//    public List<UserDTO> findSearchUsers(String text) {
//        List<User> searchUsers = userRepository.findSearchUsers(text);
//        return searchUsers.stream()
//                .map(user -> modelMapper.map(user, UserDTO.class))
//                .collect(Collectors.toList());
//    }
//
//    // todo :: user 정보 update... jwt exception 처리 아직 안됨..
//    public UserResponseDTO userUpdate(UserDTO userDTO, UserDetails userDetails) {
//        User user = ((UserDetailsImpl) userDetails).getUser();
//        if (user == null) {
//            return UserResponseDTO.builder().message("회원정보를 찾을 수 없습니다.").build();
//        }
//        if (user.getUserId().equals(userDTO.getUserId())) {
//            userDTO.setUserPwd(passwordEncoder.encode(userDTO.getUserPwd()));
//            int result = userRepository.updateUser(userDTO);
//            if (result > 0) {
//                              return UserResponseDTO.builder().message("회원정보가 정상적으로 수정되었습니다.").build();
//
//            }
//        }
//        return UserResponseDTO.builder().message("회원정보 수정에 실패하였습니다.").build();
//    }

    public UserResponseDTO ImageUpdate(UserDTO userDTO, MultipartFile image) throws IOException {
        String imageUrl = s3Uploader.upload(image, "user/image");
        userDTO.setUserImage(imageUrl);
        User user = User.builder()
                .userId(userDTO.getUserId())
                .userImage(userDTO.getUserImage())
                .build();
        int result = userRepository.updateImage(user);

        if (result > 0) {
            return null;
            //return UserResponseDTO.builder().message("정상적으로 수정되었습니다.").build();
        }
        return null;
        //return UserResponseDTO.builder().message("회원정보가 정상적으로 수정되었습니다.").build();

    }
}
