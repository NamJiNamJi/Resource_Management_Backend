package com.douzone.wehago.service;

import com.douzone.wehago.common.S3Uploader;
import com.douzone.wehago.domain.Device;
import com.douzone.wehago.dto.DeviceDTO;
import com.douzone.wehago.dto.DevicePageResponseDTO;
import com.douzone.wehago.dto.DeviceResponseDTO;
import com.douzone.wehago.repository.DeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeviceService {

    private final DeviceRepository deviceRepository;
    private final S3Uploader s3Uploader;

    @Transactional
    public DeviceResponseDTO saveDevice (DeviceDTO deviceDTO, MultipartFile image) throws IOException {

        String imageUrl = s3Uploader.upload(image, "device/image");

        Device device = Device.builder()
                .dvcName(deviceDTO.getDvcName())
                .dvcSerial(deviceDTO.getDvcSerial())
                .dvcImage(imageUrl)
                .dvcBuy(deviceDTO.getDvcBuy())
                .dvcExplain(deviceDTO.getDvcExplain())
                .copSeq(1)
                .rscSeq(2)
                .build();

        deviceRepository.save(device);

        return getDeivceResponseDTO(device);
    }

    @Transactional(readOnly = true)
    public DevicePageResponseDTO findAllDevice() {

        List<Device> dvcList = deviceRepository.findAll();

        List<DeviceResponseDTO> deviceResponseDTOList = new ArrayList<>();

        for (Device device : dvcList) {
            deviceResponseDTOList.add(getDeivceResponseDTO(device));
        }

        return DevicePageResponseDTO.builder()
                .dvcList(deviceResponseDTOList)
                .build();
    }

    @Transactional
    public DeviceResponseDTO findOneDevice(Integer dvcSeq) {

        Device device = deviceRepository.findOne(dvcSeq);

        return getDeivceResponseDTO(device);
    }

    @Transactional
    public DeviceResponseDTO updateDevice (DeviceDTO deviceDTO, MultipartFile image, Integer dvcSeq) throws IOException {

        String imageUrl = s3Uploader.upload(image, "device/image");
        Device device = Device.builder()
                .dvcSeq(dvcSeq)
                .dvcName(deviceDTO.getDvcName())
                .dvcSerial(deviceDTO.getDvcSerial())
                .dvcImage(imageUrl)
                .dvcBuy(deviceDTO.getDvcBuy())
                .dvcExplain(deviceDTO.getDvcExplain())
                .build();

        deviceRepository.update(device);

        return getDeivceResponseDTO(device);
    }

    public void deleteDevice (Integer dvcSeq) {
        deviceRepository.delete(dvcSeq);
    }

    private DeviceResponseDTO getDeivceResponseDTO (Device device) {
        return DeviceResponseDTO.builder()
                .dvcName(device.getDvcName())
                .dvcSerial(device.getDvcSerial())
                .dvcImage(device.getDvcImage())
                .dvcBuy(device.getDvcBuy())
                .dvcExplain(device.getDvcExplain())
                .dvcCreated(device.getDvcCreated())
                .dvcUpdated(device.getDvcUpdated())
                .dvcState(device.getDvcState())
                .copSeq(device.getCopSeq())
                .rscSeq(device.getRscSeq())
                .build();
    }
}
