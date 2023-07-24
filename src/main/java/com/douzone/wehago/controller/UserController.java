package com.douzone.wehago.controller;

import com.douzone.wehago.domain.User;
import com.douzone.wehago.dto.UserDTO;
import com.douzone.wehago.dto.UserLoginDTO;
import com.douzone.wehago.dto.UserRegisterDTO;
import com.douzone.wehago.security.UserDetailsImpl;
import com.douzone.wehago.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // todo :: 나중에 전체적으로 ResponseEntity.ok() 조건에 따라 수정해야됨.

    // todo :: user 회원가입 Controller
    @PostMapping("/api/user/signup")
    public ResponseEntity<?> userSignUp(@RequestBody @Valid UserRegisterDTO userRegisterDTO) {
        userService.userRegister(userRegisterDTO);
        return ResponseEntity.ok().body("회원가입 성공!!");
    }

    // todo :: user 회원가입 id check Controller
    @PostMapping("/api/user/duplicate")
    public ResponseEntity<?> idCheck(@RequestParam String userId) {
        return ResponseEntity.ok().body(userService.userIdCheck(userId));
    }

    // todo :: user 로그인 Controller
    @PostMapping("/api/user/login")
    public ResponseEntity<?> userLogin(@RequestBody @Valid UserLoginDTO userLoginDTO) {
        return ResponseEntity.ok().body(userService.userLogin(userLoginDTO));
    }

    // todo :: 모든 회원 조회 (사원 x) Controller, 지금은 토큰값 없이도 되게.. (error 처리 로직 작성 안됨)
    @PostMapping("/api/user/all")
    public ResponseEntity<?> userFindAll() { // 나중에는 @AuthenticationPrincipal
        return ResponseEntity.ok().body(userService.findAllUsers());
    }

    // todo :: 검색으로 특정회원 조회 (사원 x) full text 사용하지않고 일단 like 로 작성, 지금은 토큰값 없이도 되게.. (error 처리 로직 작성 안됨)
    @PostMapping("/api/user/search")
    public ResponseEntity<?> userSearch(@RequestParam String searchText) {
        return ResponseEntity.ok().body(userService.findSearchUsers(searchText));
    }

    // todo :: 유저 정보 update .. custom Exception 만들어지면 jwt exception 도 추가로 넣어야 클라이언트로 오류 메시지 반환할 수 있을 듯..
    @PostMapping("/api/user/update")
    public ResponseEntity<?> userUpdate(@RequestBody UserDTO userDTO,
                                        @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok().body(userService.userUpdate(userDTO, userDetails));
    }

    // todo :: axios 를 통해 accessToken 전달시 userDetails 가 잘 들어가는지..
    @PostMapping("/api/header/test")
    public ResponseEntity<?> testUserDetails(@AuthenticationPrincipal UserDetails userDetails) {
        System.out.println(userDetails);
        User user = ((UserDetailsImpl) userDetails).getUser();
        System.out.println(user.getUserId());
        System.out.println(user.getUserName());
        System.out.println(user.getUserEmail());
        return null;
    }
}
