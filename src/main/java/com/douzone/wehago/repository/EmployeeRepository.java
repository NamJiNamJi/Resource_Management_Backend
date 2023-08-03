package com.douzone.wehago.repository;

import com.douzone.wehago.domain.Employee;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class EmployeeRepository {

    private final SqlSession sqlSession;

    public void save(Employee employee) {
        sqlSession.insert("com.douzone.wehago.mapper.EmployeeMapper.save", employee);
    }

    public List<Employee> findAll(Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        return sqlSession.selectList("com.douzone.wehago.mapper.EmployeeMapper.findAll");
    }

    public Employee findOne(Integer empSeq) {
        return sqlSession.selectOne("com.douzone.wehago.mapper.EmployeeMapper.findOne", empSeq);
    }

    public Integer update(Employee employee) {
        return sqlSession.update("com.douzone.wehago.mapper.EmployeeMapper.update", employee);
    }

    public void delete(Employee employee) {
        sqlSession.delete("com.douzone.wehago.mapper.EmployeeMapper.delete", employee);
    }


}
