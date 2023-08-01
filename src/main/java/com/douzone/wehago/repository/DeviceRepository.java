package com.douzone.wehago.repository;

import com.douzone.wehago.domain.Device;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DeviceRepository {

    private final SqlSession sqlSession;

    public void save(Device device) {
        sqlSession.insert("com.douzone.wehago.mapper.DeviceMapper.save", device);
    }

    public List<Device> findAll() {
        return  sqlSession.selectList("com.douzone.wehago.mapper.DeviceMapper.findAll");
    }

    public Device findOne(Integer dvc_seq) {
        return sqlSession.selectOne("com.douzone.wehago.mapper.DeviceMapper.findOne", dvc_seq);
    }

    public Integer update(Device device) {
        return sqlSession.update("com.douzone.wehago.mapper.DeviceMapper.update", device);
    }

    public void delete(Integer dvc_seq) {
        sqlSession.delete("com.douzone.wehago.mapper.DeviceMapper.delete", dvc_seq);
    }
}
