package com.douzone.wehago.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TestResponseDTO {
    private Integer testSeq;
    private String testData1;
    private String testData2;
    private String testData3;
    private String imgUrl;
}
