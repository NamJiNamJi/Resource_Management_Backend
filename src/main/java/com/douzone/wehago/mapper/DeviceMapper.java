package com.douzone.wehago.mapper;

import com.douzone.wehago.dto.DeviceDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface DeviceMapper {

    void insertDevice(DeviceDTO device);
    DeviceDTO selectDeviceById(Long dvcSeq);

    List<DeviceDTO> selectAllDevices();

    void updateDevice(DeviceDTO device);

    void deleteDevice(Long dvcSeq);
}
