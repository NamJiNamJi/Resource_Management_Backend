package com.douzone.wehago.service;

import com.douzone.wehago.domain.Member;
import com.douzone.wehago.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void save(Member member){
        memberRepository.save(member);
    }

    public List<Member> getAllMembers(){
        return memberRepository.findAll();
    }

    public Member getMemberById(String memberId){
        return memberRepository.findOne(memberId);
    }

    public void updateMember(Member member){
        memberRepository.update(member);
    }

    public void deleteMember(String memberId){
        memberRepository.delete(memberId);
    }




}
