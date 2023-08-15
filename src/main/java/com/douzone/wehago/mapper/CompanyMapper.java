package com.douzone.wehago.mapper;

import com.douzone.wehago.domain.Company;
import com.douzone.wehago.dto.company.CompanyDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CompanyMapper {
    CompanyDTO save(Company company);

    List<Company> findAll();
    List<Company> findAllByCopSeq();

    Company findOne();

    void update(Company company);

    void delete(Integer copSeq);
}
