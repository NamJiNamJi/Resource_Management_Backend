package com.douzone.wehago.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
//@NoArgsConstructor
//@AllArgsConstructor
@ToString
public class FacilityDTO {

    @NotBlank(message = "차량명은 필수 입력값입니다.")
    @Size(min = 1, message = "차량명은 최소 1자 이상 입력해야 합니다.")
    private String carName;
    @NotBlank(message = "차량번호는 필수 입력값입니다.")
    @Size(min = 7, message = "차량번호는 앞번호와 뒷번호를 모두 입력해야 합니다.")
    private String carNumber;
    @NotBlank(message = "주행거리는 필수 입력값입니다.")
    private String carDistance;
    private String carExplan;
    @NotBlank(message = "차량 연식은 필수 입력값입니다.")
    private LocalDate carYear;
//    private byte[] carImage;

}
