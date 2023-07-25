package com.douzone.wehago.service;

import com.douzone.wehago.domain.Facility;
import com.douzone.wehago.dto.FacilityDTO;
import com.douzone.wehago.repository.FacilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacilityService {

    @Autowired
    private FacilityRepository facilityRepository;

    public void save(Facility facility) {
        facilityRepository.save(facility);
    }

    public List<Facility> findAll() {
        return facilityRepository.findAll();
    }
}
