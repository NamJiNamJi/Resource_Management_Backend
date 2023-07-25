package com.douzone.wehago.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class FacilityImgInfoDTO {
    private MultipartFile objectImg; //이미지 객체
}
