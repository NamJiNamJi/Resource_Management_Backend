package com.douzone.wehago.service;


import com.douzone.wehago.dto.DeviceDTO;
import com.douzone.wehago.repository.DeviceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class DeviceService {

    private final DeviceRepository deviceRepository;

    public void saveDevice(DeviceDTO device) {
        deviceRepository.insertDevice(device);
    }
//
//    public Device getDeviceById(Long dvcSeq) {
//        return deviceRepository.selectDeviceById(dvcSeq);
//    }
//
//    public List<Device> findAll() {
//        return deviceRepository.findAll();
//    }

//    public void updateDevice(Device device) {
//        deviceRepository.updateDevice(device);
//    }
//
//    public void deleteDevice(Long dvcSeq) {
//        deviceRepository.deleteDevice(dvcSeq);
//    }

}
