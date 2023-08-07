package com.douzone.wehago.repository;

import com.douzone.wehago.domain.Device;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public List<Device> searchDevice(String columnName, String searchString) {
        Map<String, String> map = new HashMap<>();
        map.put("columnName", converCamelToSnakeCase(columnName));
        map.put("searchString", searchString);
        return sqlSession.selectList("com.douzone.wehago.mapper.CarMapper.searchDevice", map);
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

    private String converCamelToSnakeCase(String camelCase) {

        StringBuilder snakeCase = new StringBuilder();

        for (char c : camelCase.toCharArray()) {
            if (Character.isUpperCase(c)) {
                snakeCase.append('_').append(Character.toLowerCase(c));
            } else {
                snakeCase.append(c);
            }
        }
        return snakeCase.toString();
    }
}
