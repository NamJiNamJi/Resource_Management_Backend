package com.douzone.wehago.service;

import com.douzone.wehago.common.S3Uploader;
import com.douzone.wehago.domain.Employee;
import com.douzone.wehago.dto.employee.EmployeeDTO;
import com.douzone.wehago.dto.employee.EmployeePageResponseDTO;
import com.douzone.wehago.dto.employee.EmployeeResponseDTO;
import com.douzone.wehago.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final S3Uploader s3Uploader;

    // 사원 등록
    @Transactional
    public EmployeeResponseDTO saveEmployee(EmployeeDTO employeeDTO) {

        Employee employee = Employee.builder()
                .empName(employeeDTO.getEmpName())
                .empPosition(employeeDTO.getEmpPosition())
                .copSeq(employeeDTO.getCopSeq()) // 프론트단에서 회사 관리자 회사값 가져와서 임의로 넣어줘야함
                .userSeq(employeeDTO.getUserSeq()) // 먼저 회원 insert 하고 seq 가져와야함
                .authLevel(employeeDTO.getAuthLevel())
                .build();

        Employee test = employeeRepository.save(employee);
        log.info("사원 일련번호 : " + test.getEmpSeq());

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
    public EmployeeResponseDTO updateEmployee(EmployeeDTO employeeDTO, MultipartFile image, Integer empSeq) throws IOException {

        Employee employee = employeeRepository.findOne(empSeq);
        String imageUrl = employee.getEmpImage();

        // 이미지가 수정된 경우만 이미지 삭제 후 업로드
        if (imageUrl != null) {
            s3Uploader.deleteImage(imageUrl, "employee/image");
            imageUrl = s3Uploader.upload(image, "employee/image");
        }

         employee = Employee.builder()
                .empSeq(empSeq)
                .empName(employeeDTO.getEmpName())
                .empPosition(employeeDTO.getEmpPosition())
                .empImage(imageUrl)
                .copSeq(employeeDTO.getCopSeq())
                .userSeq(employeeDTO.getUserSeq())
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
                .empSeq(employee.getEmpSeq())
                .empName(employee.getEmpName())
                .empPosition(employee.getEmpPosition())
                .empImage(employee.getEmpImage())
                .copSeq(employee.getCopSeq())
                .userSeq(employee.getUserSeq())
                .empState(employee.getEmpState())
                .authLevel(employee.getAuthLevel())
                .build();
    }

}
