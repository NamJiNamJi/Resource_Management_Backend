package com.douzone.wehago.mapper;

import com.douzone.wehago.domain.Employee;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    void save(Employee employee);

    List<Employee> findAll();

    Employee findOne();

    void update(Employee employee);

//    void updateState(Employee employee);

    void delete(Employee employee);

//    void delete(Integer empSeq);
}
