package com.douzone.wehago.service;

import com.douzone.wehago.common.S3Uploader;
import com.douzone.wehago.domain.Employee;
import com.douzone.wehago.dto.employee.EmployeeDTO;
import com.douzone.wehago.dto.employee.EmployeePageResponseDTO;
import com.douzone.wehago.dto.employee.EmployeeResponseDTO;
import com.douzone.wehago.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final S3Uploader s3Uploader;

    @Transactional
    public EmployeeResponseDTO saveEmployee(EmployeeDTO employeeDTO, MultipartFile image) throws IOException {
        String imageUrl = s3Uploader.upload(image, "employee/image");

        Employee employee = Employee.builder()
                .empName(employeeDTO.getEmpName())
                .empImage(imageUrl)
                .copSeq(employeeDTO.getCopSeq())
                .userSeq(employeeDTO.getUserSeq())
                .authLevel(employeeDTO.getAuthLevel())
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
                .empImage(imageUrl)
                .copSeq(employeeDTO.getCopSeq())
                .userSeq(employeeDTO.getUserSeq())
                .authLevel(employeeDTO.getAuthLevel())
                .build();

        employeeRepository.update(employee);

        return getEmployeeResponseDTO(employee);
    }

    public void deleteEmployee(Integer empSeq) {
        employeeRepository.delete(empSeq);
    }


    EmployeeResponseDTO getEmployeeResponseDTO(Employee employee) {
        return EmployeeResponseDTO.builder()
                .empSeq(employee.getEmpSeq())
                .empName(employee.getEmpName())
                .empImage(employee.getEmpImage())
                .copSeq(employee.getCopSeq())
                .userSeq(employee.getUserSeq())
                .empState(employee.getEmpState())
                .authLevel(employee.getAuthLevel())
                .build();
    }

}
