package com.douzone.wehago.service;

import com.douzone.wehago.domain.Company;
import com.douzone.wehago.domain.Employee;
import com.douzone.wehago.dto.*;
import com.douzone.wehago.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Transactional
    public EmployeeResponseDTO saveEmployee(EmployeeDTO employeeDTO) {
        Employee employee = Employee.builder()
                .empId(employeeDTO.getEmpId())
                .empName(employeeDTO.getEmpName())
                .copSeq(employeeDTO.getCopSeq())
                .rscAdmin(employeeDTO.getRscAdmin())
                .build();

        employeeRepository.save(employee);

        // 조회 메서드 추가하면 응답 id 까지 넣을 수 있음

        return getEmployeeResponseDTO(employee);
    }

    @Transactional(readOnly = true)
    public EmployeePageResponseDTO findAll() {
        List<Employee> employeeList = employeeRepository.findAll();

        List<EmployeeResponseDTO> employeeResponseDTOList = new ArrayList<>();

        for(Employee employee : employeeList) {
            employeeResponseDTOList.add(getEmployeeResponseDTO(employee));
        }

        return EmployeePageResponseDTO.builder()
                .employeeList(employeeResponseDTOList)
                .build();
    }

    @Transactional(readOnly = true)
    public EmployeeResponseDTO findOne(Integer empSeq) {
        Employee employee = employeeRepository.findOne(empSeq);

        return getEmployeeResponseDTO(employee);
    }




    EmployeeResponseDTO getEmployeeResponseDTO(Employee employee) {
        return EmployeeResponseDTO.builder()
                .empSeq(employee.getEmpSeq())
                .empId(employee.getEmpId())
                .empName(employee.getEmpName())
                .copSeq(employee.getCopSeq())
                .rscAdmin(employee.getRscAdmin())
                .build();
    }


}
