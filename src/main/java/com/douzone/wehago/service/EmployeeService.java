package com.douzone.wehago.service;

import com.douzone.wehago.common.S3Uploader;
import com.douzone.wehago.domain.Employee;
import com.douzone.wehago.domain.User;
import com.douzone.wehago.dto.employee.EmployeeDTO;
import com.douzone.wehago.dto.employee.EmployeePageResponseDTO;
import com.douzone.wehago.dto.employee.EmployeeResponseDTO;
import com.douzone.wehago.repository.EmployeeRepository;
import com.douzone.wehago.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final S3Uploader s3Uploader;

    @Transactional
    public ResponseEntity<?> saveEmployee(EmployeeDTO employeeDTO, UserDetails userDetails) {

        User user = ((UserDetailsImpl) userDetails).getUser();

        if (user == null) {
            String errorMessage = "토큰이 만료되었거나, 회원정보를 찾을 수 없습니다.";
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorMessage);
        }

        Employee employee = Employee.builder()
                .empName(employeeDTO.getEmpName())
                .empPosition(employeeDTO.getEmpPosition())
//                .empImage('')
                .copSeq(employeeDTO.getCopSeq())
                .userSeq(employeeDTO.getUserSeq())
                .authLevel(employeeDTO.getAuthLevel())
                .build();

        employeeRepository.save(employee);

        return ResponseEntity.status(HttpStatus.CREATED).body(getEmployeeResponseDTO(employee));
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
