package com.douzone.wehago.mapper;

import com.douzone.wehago.domain.Device;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface DeviceMapper {

    void save(Device device);

    List<Device> findAll();

    Device findOne(Integer dvc_seq);

    List<Device> searchDevice(Map<String, Object> dataValues);

    void update (Device device);

    void delete(Integer dvc_seq);
}
