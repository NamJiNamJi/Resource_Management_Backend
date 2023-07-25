package com.douzone.wehago.mapper;

import com.douzone.wehago.domain.Test;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestMapper {
    void save(Test test);
}
