package com.douzone.wehago.service;

import com.douzone.wehago.domain.Facility;
import com.douzone.wehago.dto.FacilityDTO;
import com.douzone.wehago.dto.FacilityPageResponseDTO;
import com.douzone.wehago.dto.FacilityResponseDTO;
import com.douzone.wehago.repository.FacilityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class FacilityService {

    private final FacilityRepository facilityRepository;

    @Transactional
    public FacilityResponseDTO save(FacilityDTO facilityDTO) {

        Facility facility = Facility.builder()
                .carName(facilityDTO.getCarName())
                .carNumber(facilityDTO.getCarNumber())
                .carDistance(facilityDTO.getCarDistance())
                .carYear(facilityDTO.getCarYear())
                .carExplan(facilityDTO.getCarExplan())
                .build();

            facilityRepository.save(facility);

            return getFacilityResponseDTO(facility);
    }

    @Transactional(readOnly = true)
    public FacilityPageResponseDTO findAll() {

        List<Facility> facilityList = facilityRepository.findAll();
        log.info(facilityList);

        List<FacilityResponseDTO> facilityResponseDTOList = new ArrayList<>();
        log.info(facilityResponseDTOList);

        for (Facility facility : facilityList) {
            facilityResponseDTOList.add(getFacilityResponseDTO(facility));
            log.info(facilityResponseDTOList);
        }

        return FacilityPageResponseDTO.builder()
                .facilityList(facilityResponseDTOList)
                .build();
    }

    private FacilityResponseDTO getFacilityResponseDTO (Facility facility) {

        return FacilityResponseDTO.builder()
                .carSeq(facility.getCarSeq())
                .carName(facility.getCarName())
                .carNumber(facility.getCarNumber())
                .carDistance(facility.getCarDistance())
                .carYear(facility.getCarYear())
                .carExplan(facility.getCarExplan())
                .build();
    }

}
