package com.douzone.wehago.service;

import com.douzone.wehago.domain.Employee;
import com.douzone.wehago.dto.EmployeeDTO;
import com.douzone.wehago.dto.EmployeeResponseDTO;
import com.douzone.wehago.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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




    EmployeeResponseDTO getEmployeeResponseDTO(Employee employee) {
        EmployeeResponseDTO employeeResponseDTO = EmployeeResponseDTO.builder()
                .empId(employee.getEmpId())
                .empName(employee.getEmpName())
                .copSeq(employee.getCopSeq())
                .rscAdmin(employee.getRscAdmin())
                .build();
        return employeeResponseDTO;
    }

}
