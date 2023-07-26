package com.douzone.wehago.service;

import com.douzone.wehago.common.S3Uploader;
import com.douzone.wehago.domain.Test;
import com.douzone.wehago.dto.TestDTO;
import com.douzone.wehago.dto.TestResponseDTO;
import com.douzone.wehago.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Log4j2
@Service
@RequiredArgsConstructor
public class TestService {

    private final TestRepository testRepository;
    private final S3Uploader s3Uploader;

    @Transactional
    public TestResponseDTO saveTest(TestDTO testDTO, MultipartFile image) throws IOException {

        String imageUrl = s3Uploader.upload(image, "test/image");

        Test test = Test.builder()
                .testData1(testDTO.getTestData1())
                .testData2(testDTO.getTestData2())
                .testData3(testDTO.getTestData3())
                .imgUrl(imageUrl)
                .build();
        log.info(test.getTestData1());
        log.info(test.getImgUrl());

        testRepository.save(test);

        return TestResponseDTO.builder()
                .testData1(testDTO.getTestData1())
                .testData2(testDTO.getTestData2())
                .testData3(testDTO.getTestData3())
                .imgUrl(imageUrl)
                .build();
    }
}
