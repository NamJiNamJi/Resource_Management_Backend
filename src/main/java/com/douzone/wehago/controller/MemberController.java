package com.douzone.wehago.controller;

import com.douzone.wehago.dto.LoginDTO;
import com.douzone.wehago.dto.MemberLoginFailResponse;
import com.douzone.wehago.dto.MemberLoginResponse;
import com.douzone.wehago.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@AllArgsConstructor
public class
MemberController {

    private final MemberService memberService;

    @ResponseBody
    @GetMapping("test")
    public String conn(){
        return "연결성공";
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("member")
    @ResponseBody
    public Object login(@RequestBody @Validated LoginDTO loginDTO, BindingResult bindingResult) {

        if(bindingResult.hasErrors()){
            log.info("아이디 또는 비밀번호가 비어있음");
            MemberLoginFailResponse failResponse = MemberLoginFailResponse.builder()
                    .ok("fail")
                    .message("아이디 또는 비밀번호가 비어있음")
                    .build();
                    return failResponse;
        }

        MemberLoginResponse memberLoginResponse = MemberLoginResponse.builder()
                .ok("ok")
                .message("로그인 성공")
                .build();
        log.info("{}", loginDTO);
        return memberLoginResponse;
    }
}
