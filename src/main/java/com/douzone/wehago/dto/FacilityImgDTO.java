package com.douzone.wehago.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class FacilityImgDTO {

    private byte[] carImage; // [java.lang.String => postgresql.bytea]으로 변환(삽입용)

    private String imageDataUrl; // [postgresql.bytea => java.lang.String]으로 변환(조회용)

}
