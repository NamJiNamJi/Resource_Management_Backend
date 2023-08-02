package com.douzone.wehago.mapper;

import com.douzone.wehago.domain.Device;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeviceMapper {

    void save(Device device);

    List<Device> findAll();

    Device findOne(Integer dvc_seq);

    void update (Device device);

    void delete(Integer dvc_seq);
}
