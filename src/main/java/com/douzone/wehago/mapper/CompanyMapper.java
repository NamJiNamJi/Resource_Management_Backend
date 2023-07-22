package com.douzone.wehago.mapper;

import com.douzone.wehago.domain.Company;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CompanyMapper {
    void save(Company company);
}
