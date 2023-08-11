package com.douzone.wehago.service;

import com.douzone.wehago.common.S3Uploader;
import com.douzone.wehago.common.exception.BusinessException;
import com.douzone.wehago.common.exception.ErrorCode;
import com.douzone.wehago.domain.Employee;
import com.douzone.wehago.domain.User;
import com.douzone.wehago.dto.employee.EmployeeDTO;
import com.douzone.wehago.dto.employee.EmployeePageResponseDTO;
import com.douzone.wehago.dto.employee.EmployeeResponseDTO;
import com.douzone.wehago.repository.EmployeeRepository;
import com.douzone.wehago.repository.UserRepository;
import com.douzone.wehago.security.UserDetailsImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;

    // 사원 등록 1
    @Transactional
    public EmployeeResponseDTO saveEmployee(EmployeeDTO employeeDTO, UserDetails userDetails) {

        User user = ((UserDetailsImpl) userDetails).getUser();

        if (user == null) {
            throw new BusinessException("토큰이 만료되었거나, 회원정보를 찾을 수 없습니다.", ErrorCode.JWT_INVALID_TOKEN);
        }

        Employee employee = Employee.builder()
                .empName(employeeDTO.getEmpName())
                .empPosition(employeeDTO.getEmpPosition())
                .copSeq(employeeDTO.getCopSeq())
                .userSeq(user.getUserSeq())
                .authLevel(employeeDTO.getAuthLevel())
                .build();

        Employee newEmployee = employeeRepository.save(employee);
//        log.info("사원 일련번호 : " + newEmployee.getEmpSeq());

        return EmployeeResponseDTO.builder()
                .empSeq(newEmployee.getEmpSeq())
                .empName(employeeDTO.getEmpName())
                .empPosition(employeeDTO.getEmpPosition())
                .authLevel(employeeDTO.getAuthLevel())
                .copSeq(employeeDTO.getCopSeq())
                .build();

    }

    // 사원 등록 2
    @Transactional
    public EmployeeResponseDTO addEmployee(EmployeeDTO employeeDTO, UserDetails userDetails) {

        User user = ((UserDetailsImpl) userDetails).getUser();

        if (user == null) {
            throw new BusinessException("토큰이 만료되었거나, 회원정보를 찾을 수 없습니다.", ErrorCode.JWT_INVALID_TOKEN);
        }

        User newUser = userRepository.saveEmployee(employeeDTO.getEmpName());
        log.info("새로운회원 일련번호 : " + newUser.getUserSeq());
        log.info("회사 일련번호 : " + employeeDTO.getCopSeq());

        Employee employee = Employee.builder()
                .empName(employeeDTO.getEmpName())
                .empPosition(employeeDTO.getEmpPosition())
                .copSeq(employeeDTO.getCopSeq())
                .userSeq(newUser.getUserSeq())
                .authLevel(employeeDTO.getAuthLevel())
                .build();

        Employee newEmployee = employeeRepository.save(employee);
        log.info("사원 일련번호 : " + newEmployee.getEmpSeq());

        return EmployeeResponseDTO.builder()
                .userSeq(newUser.getUserSeq())
                .empSeq(newEmployee.getEmpSeq())
                .empName(employeeDTO.getEmpName())
                .empPosition(employeeDTO.getEmpPosition())
                .authLevel(employeeDTO.getAuthLevel())
                .copSeq(employeeDTO.getCopSeq())
                .build();

    }

    @Transactional(readOnly = true)
    public EmployeePageResponseDTO findAll(int pageNo , int pageSize, UserDetails userDetails) {

        User user = ((UserDetailsImpl) userDetails).getUser();

        if (user == null) {
            throw new BusinessException("토큰이 만료되었거나, 회원정보를 찾을 수 없습니다.", ErrorCode.JWT_INVALID_TOKEN);
        }

        List<Employee> employeeList = employeeRepository.findAll(pageNo, pageSize, user.getCopSeq());
        Object total =((Page) employeeList).getPages();
        List<EmployeeResponseDTO> employeeResponseDTOList = new ArrayList<>();

        for(Employee employee : employeeList) {
            employeeResponseDTOList.add(getEmployeeResponseDTO(employee));
        }

        return EmployeePageResponseDTO.builder()
                .employeeList(employeeResponseDTOList)
                .total(total)
                .build();
    }


    // 사원 검색
    @Transactional(readOnly = true)
    public EmployeePageResponseDTO searchEmployee(String type, String keyword) {
        List<Employee> employeeList = employeeRepository.searchEmployee(type, keyword);
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

    @Transactional
    public EmployeeResponseDTO updateEmployee(EmployeeDTO employeeDTO, Integer empSeq, UserDetails userDetails) {

        User user = ((UserDetailsImpl) userDetails).getUser();

        if (user == null) {
            throw new BusinessException("토큰이 만료되었거나, 회원정보를 찾을 수 없습니다.", ErrorCode.JWT_INVALID_TOKEN);
        }

         Employee employee = Employee.builder()
                .empSeq(empSeq)
                .empName(employeeDTO.getEmpName())
                .empPosition(employeeDTO.getEmpPosition())
                .copSeq(user.getCopSeq())
                .userSeq(user.getUserSeq())
                .authLevel(employeeDTO.getAuthLevel())
                .empUpdated(new Timestamp(System.currentTimeMillis()))
                .build();

        employeeRepository.update(employee);

        return getEmployeeResponseDTO(employee);
    }

    @Transactional
    public void deleteEmployee(Integer empSeq) {

        Employee employee = Employee.builder()
               .empSeq(empSeq)
               .empState(false)
               .empUpdated(new Timestamp(System.currentTimeMillis()))
               .build();

        employeeRepository.delete(employee);
    }


    EmployeeResponseDTO getEmployeeResponseDTO(Employee employee) {
        return EmployeeResponseDTO.builder()
                .userSeq(employee.getUserSeq())
                .empSeq(employee.getEmpSeq())
                .empName(employee.getEmpName())
                .empPosition(employee.getEmpPosition())
                .copSeq(employee.getCopSeq())
                .authLevel(employee.getAuthLevel())
                .build();
    }
}
