package com.douzone.wehago;

import com.douzone.wehago.domain.Car;
import com.douzone.wehago.dto.CarDTO;
import com.douzone.wehago.mapper.CarMapper;
import com.douzone.wehago.repository.CarRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.channels.AcceptPendingException;
import java.util.List;


@SpringBootTest
class WehagoApplicationTests {

    @Autowired
    CarMapper carMapper;

    @Test
    void findAllTest() {
        List<Car> carList = carMapper.findAll();
        for (Car car : carList) {
            System.out.println("Car : " + car.toString());
        }
    }

//    @Test
    void save() {
        for (int i=1; i<=10; i++) {
            Car car = new Car();
            car.setCarSeq(i);
            car.setCarNumber("32너6099");
            car.setCarType("프라이드");
            car.setCarImage(null);
            car.setCarYear(1995);
            car.setCarDistance(120000);
            carMapper.save(car);

            List<Car> post = carMapper.findAll();
        }
        for (int i=11; i<=20; i++) {
            Car car = new Car();
            car.setCarSeq(i);
            car.setCarNumber("32너6625");
            car.setCarType("sm6");
            car.setCarImage(null);
            car.setCarYear(1995);
            car.setCarDistance(120000);
            carMapper.save(car);

            List<Car> post = carMapper.findAll();
        }

    }

//    @Test
    void contextLoads() {

    }

}
