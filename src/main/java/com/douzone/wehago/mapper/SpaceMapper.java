package com.douzone.wehago.mapper;

import com.douzone.wehago.domain.Space;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SpaceMapper {

    /* 공간자원 정보 저장 Insert */
    void save(Space space);

    /* 공간자원 선택 조회 Select */
    Space findOne(Integer spc_seq);

    /* 공간자원 전체 조회 Select */
    List<Space> findAll();

    /* 공간자원 정보 수정 Update */
    void update(Space space);

    /* 공간자원 정보 삭제 Delete */
    Space delete(Space space);

//    /* 공간자원 정보 삭제 Delete */
//    void delete(Integer spc_seq);
}
