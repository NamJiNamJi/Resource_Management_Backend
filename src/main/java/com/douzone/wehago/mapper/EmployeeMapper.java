package com.douzone.wehago.mapper;

import com.douzone.wehago.domain.Employee;
import com.douzone.wehago.dto.employee.EmployeeDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface EmployeeMapper {

    Employee save(Employee employee);

    List<Employee> findAll();

    Employee findOne();

    void update(Employee employee);
    List<Employee> searchEmployee(Map<String, Object> dataValues);

//    void updateState(Employee employee);

    void delete(Employee employee);

//    void delete(Integer empSeq);
}
