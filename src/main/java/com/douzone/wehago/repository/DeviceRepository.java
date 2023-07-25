package com.douzone.wehago.repository;


import com.douzone.wehago.domain.Device;
import com.douzone.wehago.dto.DeviceDTO;
import com.douzone.wehago.mapper.DeviceMapper;
import lombok.AllArgsConstructor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@AllArgsConstructor
public class DeviceRepository {

    private final SqlSession sqlSession;


    public void insertDevice(DeviceDTO device) {
        sqlSession.insert("com.douzone.wehago.mapper.DeviceMapper.insertDevice", device);
    }
//
    public DeviceDTO selectDeviceById(Long dvcSeq) {
        return sqlSession.selectOne("com.douzone.wehago.mapper.DeviceMapper.selectDeviceById", dvcSeq);
    }

    public List<DeviceDTO> findAll() {
        return sqlSession.selectList("com.douzone.wehago.mapper.DeviceMapper.findAll");
    }

    public void updateDevice(DeviceDTO device) {
        sqlSession.update("com.douzone.wehago.mapper.DeviceMapper.updateDevice", device);
    }
//
    public void deleteDevice(Long dvcSeq) {
        sqlSession.delete("com.douzone.wehago.mapper.DeviceMapper.deleteDevice", dvcSeq);
    }

}
