package com.douzone.wehago.controller;

import com.douzone.wehago.dto.UserLoginDTO;
import com.douzone.wehago.dto.UserRegisterDTO;
import com.douzone.wehago.jwt.TokenDTO;
import com.douzone.wehago.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // todo :: user 회원가입 Controller
    @PostMapping("/api/user/signup")
    public ResponseEntity<?> userSignUp(@RequestBody @Valid UserRegisterDTO userRegisterDTO) {
        userService.userRegister(userRegisterDTO);
        return ResponseEntity.ok().body("회원가입 성공!!");
    }

    // todo :: user 로그인 Controller
    @PostMapping("/api/user/login")
    public ResponseEntity<?> userLogin(@RequestBody @Valid UserLoginDTO userLoginDTO) {
        TokenDTO tokenDTO = userService.login(userLoginDTO);
        return ResponseEntity.ok().body(tokenDTO);
    }
}
