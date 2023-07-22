package com.douzone.wehago.mapper;

import com.douzone.wehago.domain.Company;
import com.douzone.wehago.domain.Member;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CompanyMapper {
    void save(Company company);

    List<Company> findAll();

    Company findOne();

    void update(Company company);
}
