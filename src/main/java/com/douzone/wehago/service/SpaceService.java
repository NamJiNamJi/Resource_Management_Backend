package com.douzone.wehago.service;

import com.douzone.wehago.common.S3Uploader;
import com.douzone.wehago.domain.Space;
import com.douzone.wehago.dto.space.SpaceDTO;
import com.douzone.wehago.dto.space.SpacePageResponseDTO;
import com.douzone.wehago.dto.space.SpaceResponseDTO;
import com.douzone.wehago.repository.SpaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SpaceService {

    private final SpaceRepository spaceRepository;
    private final S3Uploader s3Uploader;

    @Transactional
    public SpaceResponseDTO saveSpace (SpaceDTO spaceDTO, MultipartFile image) throws IOException {

        String iamgeUrl = s3Uploader.upload(image, "space/image");

        Space space = Space.builder()
                .spcName(spaceDTO.getSpcName())
                .spcCap(spaceDTO.getSpcCap())
                .spcImage(iamgeUrl)
                .spcExplain(spaceDTO.getSpcExplain())
                .copSeq(3)
                .rscSeq(4)
                .build();
        spaceRepository.save(space);

        return getSpaceResponseDTO(space);
    }

    @Transactional
    public SpacePageResponseDTO findAllSpace() {

        List<Space> list = spaceRepository.findAll();

        List<SpaceResponseDTO> spaceResponseDTOList = new ArrayList<>();

        for (Space space : list) {
            spaceResponseDTOList.add(getSpaceResponseDTO(space));
        }

        return SpacePageResponseDTO.builder()
                .list(spaceResponseDTOList)
                .build();
    }

    @Transactional
    public SpaceResponseDTO findOneSpace (Integer spcSeq) {
        Space space = spaceRepository.findOne(spcSeq);

        return getSpaceResponseDTO(space);
    }

    @Transactional(readOnly = true)
    public SpacePageResponseDTO searchSpace (String columnName, String searchString) {
        List<Space> list = spaceRepository.searchSpace(columnName, searchString);
        System.out.println("Service : " + columnName + searchString);
        List<SpaceResponseDTO> spaceResponseDTOList = new ArrayList<>();

        for (Space space : list) {
            spaceResponseDTOList.add(getSpaceResponseDTO(space));
        }

        return SpacePageResponseDTO.builder()
                .list(spaceResponseDTOList)
                .build();
    }

    @Transactional
    public SpaceResponseDTO updateSpace (SpaceDTO spaceDTO, Integer spcSeq) {
        Space space = Space.builder()
                .spcSeq(spcSeq)
                .spcName(spaceDTO.getSpcName())
                .spcCap(spaceDTO.getSpcCap())
                .spcImage(spaceDTO.getSpcImage())
                .spcExplain(spaceDTO.getSpcExplain())
                .build();
        spaceRepository.update(space);

        return getSpaceResponseDTO(space);
    }

    @Transactional
    public Integer deleteSpace (Integer spcSeq) {

        Space space = Space.builder()
                .spcSeq(spcSeq)
                .spcState(false)
                .spcUpdated(new Timestamp(System.currentTimeMillis()))
                .build();

        Space responseSpace = spaceRepository.delete(space);
        return responseSpace.getSpcSeq();
    }

//    public void deleteSpace (Integer spcSeq) {
//        spaceRepository.delete(spcSeq);
//    }

    private SpaceResponseDTO getSpaceResponseDTO (Space space) {
        return SpaceResponseDTO.builder()
                .spcSeq(space.getSpcSeq())
                .spcName(space.getSpcName())
                .spcCap(space.getSpcCap())
                .spcImage(space.getSpcImage())
                .spcExplain(space.getSpcExplain())
                .spcCreated(space.getSpcCreated())
                .spcUpdated(space.getSpcUpdated())
                .spcState(space.getSpcState())
                .copSeq(space.getCopSeq())
                .rscSeq(space.getRscSeq())
                .build();
    }
}
