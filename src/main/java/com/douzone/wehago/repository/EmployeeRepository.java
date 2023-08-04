package com.douzone.wehago.repository;

import com.douzone.wehago.domain.Employee;
import com.douzone.wehago.dto.employee.EmployeeDTO;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class EmployeeRepository {

    private final SqlSession sqlSession;


    public Employee save(Employee employee){
        return sqlSession.selectOne("com.douzone.wehago.mapper.EmployeeMapper.save", employee);
    }

    public List<Employee> findAll() {
        return sqlSession.selectList("com.douzone.wehago.mapper.EmployeeMapper.findAll");
    }

    public List<Employee> searchEmployee(String type, String keyword) {
        Map<String, String> map = new HashMap<>();
        map.put("type", converCamelToSnakeCase(type));
        map.put("keyword", keyword);
        System.out.println(map);
        return sqlSession.selectList("com.douzone.wehago.mapper.EmployeeMapper.searchEmployee", map);
    }

    public Employee findOne(Integer empSeq){
        return sqlSession.selectOne("com.douzone.wehago.mapper.EmployeeMapper.findOne", empSeq);
    }

    public Integer update(Employee employee){
        return sqlSession.update("com.douzone.wehago.mapper.EmployeeMapper.update", employee);
    }

    public void delete(Employee employee){
        sqlSession.delete("com.douzone.wehago.mapper.EmployeeMapper.delete", employee);
    }

    private String converCamelToSnakeCase(String camelCase) {

        StringBuilder snakeCase = new StringBuilder();

        for (char c : camelCase.toCharArray()) {
            if (Character.isUpperCase(c)) {
                snakeCase.append('_').append(Character.toLowerCase(c));
            } else {
                snakeCase.append(c);
            }
        }
        return snakeCase.toString();
    }


}
