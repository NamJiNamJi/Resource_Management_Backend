package com.douzone.wehago.mapper;

import com.douzone.wehago.domain.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {

    void save(Member member);

    List<Member> findAll();

    Member findOne(String memberId);

    void update(Member member);

    void delete(String memberId);
}
