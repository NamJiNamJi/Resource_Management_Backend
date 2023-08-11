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
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    @Transactional
    public Response userRegister(UserRegisterDTO userRegisterDTO) {

        try {
            // 비밀번호 암호화
            String password = passwordEncoder.encode(userRegisterDTO.getUserPwd());
            userRegisterDTO.beforeRegisterUpdate(password);

            User user = modelMapper.map(userRegisterDTO, User.class);
            userRepository.save(user);

            return new Response(HttpStatus.OK, "회원가입을 축하합니다!", null);

        } catch (Exception e) {
            return new Response(HttpStatus.BAD_REQUEST, "잘못된 요청입니다.", null);
        }
    }

    // todo :: user 회원가입 유저 Id 중복검사
    @Transactional
    public int userIdCheck(String userId) {
        return userRepository.duplicationUserId(userId);
    }

    // todo :: user 로그인 service
    @Transactional
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

            // 회사 정보가 null 이고, user_state 가 true 일 때..
            if (user.getCopSeq() == null && user.getUserState()) {

                UserResponseDTO userResponseDTO = UserResponseDTO.builder()
                        .tokenDTO(tokenDTO)
                        .userDTO(UserDTO.builder()
                                .userSeq(user.getUserSeq())
                                .userName(user.getUserName())
                                .userState(user.getUserState())
                                .build())
                        .build();

                return Response.builder()
                        .status(HttpStatus.OK)
                        .message("회사정보가 존재하지 않아 회사신청페이지로 이동합니다.")
                        .data(userResponseDTO)
                        .build();
            } else {

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
            }

            // todo :: 나중에 catch error 도 분기처리해야 할 수도 있음.
        } catch (IllegalArgumentException le) {
            log.info("아이디나 비밀번호가 일치하지 않습니다.");
            return Response.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .message("로그인 실패")
                    .build();
        }
    }

    // todo :: 비밀번호 변경
    @Transactional
    public Response updatePwd(UpdatePasswordDTO updatePasswordDTO) {

        String password = passwordEncoder.encode(updatePasswordDTO.getUserPwd());

        updatePasswordDTO.setUserPwd(password);

        int result = userRepository.updatePwd(updatePasswordDTO);

        if (result == 1) {
            return new Response(HttpStatus.OK, "비밀번호가 변경되었습니다.", null);
        }

        return new Response(HttpStatus.BAD_REQUEST, "비밀번호 변경 실패", null);

    }

    @Transactional
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

    // todo :: 회사 생성 후 유저 데이터 가져오기
    @Transactional
    public Response userComplete(UserDetails userDetails) {

        User user = ((UserDetailsImpl) userDetails).getUser();

        if (user == null) {
            String errorMessage = "토큰이 만료되었거나, 회원정보를 찾을 수 없습니다.";
            return Response.builder()
                    .status(HttpStatus.UNAUTHORIZED)
                    .message(errorMessage)
                    .build();
        }

        UserDTO userDTO = userRepository.findUserData(user.getUserSeq());

        UserResponseDTO userResponseDTO = UserResponseDTO.builder()
                .userDTO(userDTO)
                .build();

        return Response.builder()
                .status(HttpStatus.OK)
                .message("로그인 성공")
                .data(userResponseDTO)
                .build();

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
    // todo :: user 정보 update... jwt exception 처리 아직 안됨..
    @Transactional
    public ResponseEntity<?> userUpdate(UserDTO userDTO, UserDetails userDetails) {

            User user = ((UserDetailsImpl) userDetails).getUser(); // 토큰이 유효한 토큰이라면 유저 정보를 가지고 온다.

            Map<String, Object> message = new HashMap<>();

            if (user == null) {
                String errorMessage = "토큰이 만료되었거나, 회원정보를 찾을 수 없습니다.";
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorMessage);
            }

            UserDTO updateUserDTO = modelMapper.map(user, UserDTO.class);
            updateUserDTO.setCopSeq(userDTO.getCopSeq());

            int result = userRepository.updateUser(updateUserDTO);
            message.put("유저 데이터 변경 : ", result);

            return ResponseEntity.status(HttpStatus.OK).body(message);

    }

    @Transactional
    public Response invitedUpdate(UserRegisterDTO userRegisterDTO) {

        try {
            String password = passwordEncoder.encode(userRegisterDTO.getUserPwd());
            userRegisterDTO.beforeRegisterUpdate(password);

            User user = modelMapper.map(userRegisterDTO, User.class);
            userRepository.updateInvited(user);

            return new Response(HttpStatus.OK, "회원가입을 축하합니다!", null);

        } catch (Exception e) {
            return new Response(HttpStatus.BAD_REQUEST, "잘못된 요청입니다.", null);
        }

    }

    @Transactional
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
