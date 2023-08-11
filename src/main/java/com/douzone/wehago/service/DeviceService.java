package com.douzone.wehago.service;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.douzone.wehago.common.S3Uploader;
import com.douzone.wehago.domain.Car;
import com.douzone.wehago.domain.Device;
import com.douzone.wehago.domain.User;
import com.douzone.wehago.dto.car.CarPageResponseDTO;
import com.douzone.wehago.dto.car.CarResponseDTO;
import com.douzone.wehago.dto.device.DeviceDTO;
import com.douzone.wehago.dto.device.DevicePageResponseDTO;
import com.douzone.wehago.dto.device.DeviceResponseDTO;
import com.douzone.wehago.dto.reservation.ReservationDTO;
import com.douzone.wehago.repository.DeviceRepository;
import com.douzone.wehago.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeviceService {

    private final DeviceRepository deviceRepository;
    private final S3Uploader s3Uploader;


    @Transactional
    public DevicePageResponseDTO finddeviceList(ReservationDTO reservationDTO , UserDetails userDetails){
        User user = ((UserDetailsImpl) userDetails).getUser();
        reservationDTO.setCopSeq(user.getCopSeq());
        System.out.println(user.getCopSeq());
        List<Device> list = deviceRepository.finddeviceList(reservationDTO);

        List<DeviceResponseDTO> deviceResponseDTOList = new ArrayList<>();

        for (Device device : list) {
            if (device.getDvcState()) {
               deviceResponseDTOList.add(getDeviceResponseDTO(device));
            }
        }
        return DevicePageResponseDTO.builder()
                .list(deviceResponseDTOList)
                .build();
    }
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

        return getDeviceResponseDTO(device);
    }

    @Transactional(readOnly = true)
    public DevicePageResponseDTO findAllDevice() {

        List<Device> list = deviceRepository.findAll();

        List<DeviceResponseDTO> deviceResponseDTOList = new ArrayList<>();

        for (Device device : list) {
            deviceResponseDTOList.add(getDeviceResponseDTO(device));
        }

        return DevicePageResponseDTO.builder()
                .list(deviceResponseDTOList)
                .build();
    }

    @Transactional(readOnly = true)
    public DevicePageResponseDTO searchDevice (String columnName, String searchString) {
        List<Device> list = deviceRepository.searchDevice(columnName, searchString);
        System.out.println("service" + columnName + searchString);
        List<DeviceResponseDTO> deviceResponseDTOList = new ArrayList<>();

        for (Device device : list) {
            deviceResponseDTOList.add(getDeviceResponseDTO(device));
        }

        return DevicePageResponseDTO.builder()
                .list(deviceResponseDTOList)
                .build();
    }


    @Transactional
    public DeviceResponseDTO findOneDevice(Integer dvcSeq) {

        Device device = deviceRepository.findOne(dvcSeq);

        return getDeviceResponseDTO(device);
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

        return getDeviceResponseDTO(device);
    }

    public Integer deleteDevice (Integer dvcSeq) {

        Device device = Device.builder()
                .dvcSeq(dvcSeq)
                .dvcState(false)
                .dvcUpdated(new Timestamp(System.currentTimeMillis()))
                .build();

        Device responseDevice = deviceRepository.delete(device);
        return responseDevice.getDvcSeq();
    }

    private DeviceResponseDTO getDeviceResponseDTO (Device device) {
        return DeviceResponseDTO.builder()
                .dvcSeq(device.getDvcSeq())
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
