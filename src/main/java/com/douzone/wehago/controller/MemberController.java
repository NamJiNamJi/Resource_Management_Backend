package com.douzone.wehago.controller;


import com.douzone.wehago.domain.Member;
import com.douzone.wehago.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@AllArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @ResponseBody
    @GetMapping("test")
    public String test(){
        String testId = "test";
        String testName = "한태";
        String testPw = "1234";

        Member member = new Member();
        member.setMemberId(testId);
        member.setMemberName(testName);
        member.setPassword(testPw);
        memberService.save(member);

        return "ok";
    }
}
