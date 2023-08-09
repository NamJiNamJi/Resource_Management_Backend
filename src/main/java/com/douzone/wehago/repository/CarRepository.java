package com.douzone.wehago.repository;

import com.douzone.wehago.domain.Car;
import com.douzone.wehago.dto.reservation.ReservationDTO;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class CarRepository {

    private final SqlSession sqlSession;
  
    public void save(Car car) {
        sqlSession.insert("com.douzone.wehago.mapper.CarMapper.save", car);
    }

    public List<Car> findAll(){
        return sqlSession.selectList("com.douzone.wehago.mapper.CarMapper.findAll");
    }
    public List<Car> findcarList(ReservationDTO reservationDTO){
        return sqlSession.selectList("com.douzone.wehago.mapper.CarMapper.findcarList",reservationDTO);
    }
    public List<Car> searchCar(String columnName, String searchString) {
        Map<String, String> map = new HashMap<>();
        map.put("columnName", converCamelToSnakeCase(columnName));
        map.put("searchString", searchString);
        return sqlSession.selectList("com.douzone.wehago.mapper.CarMapper.searchCar", map);
    }

    public Car findOne(Integer car_seq) {
        return sqlSession.selectOne("com.douzone.wehago.mapper.CarMapper.findOne", car_seq);
    }

    public Integer update(Car car){
        return sqlSession.update("com.douzone.wehago.mapper.CarMapper.update", car);
    }

    public Car delete(Car car) {

        return sqlSession.selectOne("com.douzone.wehago.mapper.CarMapper.delete", car);
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
