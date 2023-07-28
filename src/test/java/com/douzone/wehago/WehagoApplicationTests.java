package com.douzone.wehago;

import com.douzone.wehago.dto.DeviceDTO;
import com.douzone.wehago.dto.RoomDTO;
import com.douzone.wehago.repository.DeviceRepository;
import com.douzone.wehago.repository.RoomRepository;
import org.assertj.core.api.Assertions;
import com.douzone.wehago.domain.Car;
import com.douzone.wehago.dto.CarDTO;
import com.douzone.wehago.mapper.CarMapper;
import com.douzone.wehago.repository.CarRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.nio.channels.AcceptPendingException;


@SpringBootTest
class WehagoApplicationTests {
}
//     DeviceTestCRUD
//    private final DeviceRepository deviceRepository;
//
//    @Autowired
//    WehagoApplicationTests(DeviceRepository deviceRepository) {
//        this.deviceRepository = deviceRepository;
//    }
  
//       private final RoomRepository roomRepository;

//       @Autowired
//      WehagoApplicationTests(RoomRepository roomRepository){
//           this.roomRepository = roomRepository;
//       }

//       @Test
//       public void  dbInsertTest(){
//           RoomDTO room = new RoomDTO();
//           Timestamp timestamp = new Timestamp(System.currentTimeMillis());

//           room.setSpcName("공간 명");
//           room.setSpcCap(3);
//           room.setSpcExplan("공간설명");
//           room.setSpcCreated(timestamp);
//           room.setSpcUpdated(timestamp);
//           roomRepository.insertRoom(room);
//       }

//    @Autowired
//    CarMapper carMapper;
//
//    @Test
//    void findAllTest() {
//        List<Car> carList = carMapper.findAll();
//        for (Car car : carList) {
//            System.out.println("Car : " + car.toString());
//        }
//    }
//
////    @Test
//    void save() {
//        for (int i=1; i<=10; i++) {
//            Car car = new Car();
//            car.setCarSeq(i);
//            car.setCarNumber("32너6099");
//            car.setCarType("프라이드");
//            car.setCarImage(null);
//            car.setCarYear(1995);
//            car.setCarDistance(120000);
//            carMapper.save(car);
//
//            List<Car> post = carMapper.findAll();
//        }
//        for (int i=11; i<=20; i++) {
//            Car car = new Car();
//            car.setCarSeq(i);
//            car.setCarNumber("32너6625");
//            car.setCarType("sm6");
//            car.setCarImage(null);
//            car.setCarYear(1995);
//            car.setCarDistance(120000);
//            carMapper.save(car);
//
//            List<Car> post = carMapper.findAll();
//        }
//
//    }
//
////    @Test
//    void contextLoads() {
//

//       @Test
//       public void dbselectAllTest() {
//           List<RoomDTO> selectAll = roomRepository.selectAllRooms();
//           for (RoomDTO room : selectAll) {
//             System.out.println("room = " + room);
//          }
//       }
//       @Test
//       public void dbUpdateTest(){
//           RoomDTO room = new RoomDTO();
//           Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//           room.setSpcSeq(1L);
//           room.setSpcName("김동민");
//           room.setSpcCap(2);
//           room.setSpcExplan("수정본");
//           room.setSpcCreated(timestamp);
//           room.setSpcUpdated(timestamp);
//           roomRepository.updateRoom(room);
//       }
//       @Test
//       public void dbDeleteTest(){
//           roomRepository.deleteRoom(2L);
//       }
//
//    @Test
//    public void dbInsertTest() {
//        DeviceDTO device = new DeviceDTO();
//        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//
//        device.setDvcName("testName");
//        device.setDvcSerial("testSerial");
//        device.setDvcBuy(new Date());
//        device.setDvcExplan("testExplan");
//        device.setDvcCreated(timestamp);
//        device.setDvcUpdated(timestamp);
//        deviceRepository.insertDevice(device);
//
//        Assertions.assertThat(device.getDvcSerial()).isEqualTo("testSerial");
//    }
//
//    @Test
//    public void selectAllTest() {
//        List<DeviceDTO> selectAll = deviceRepository.findAll();
//        for (DeviceDTO device : selectAll) {
//            System.out.println("device = " + device);
//        }
//    }
//
//    @Test
//    public void selectTest(){
//        DeviceDTO device = deviceRepository.selectDeviceById(3L);
//        System.out.println("device = " + device);
//    }
//    @Test
//    public void UpdateTest(){
//        DeviceDTO device = new DeviceDTO();
//        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//        device.setDvcSeq(4L);
//        device.setDvcName("testUpdateName");
//        device.setDvcSerial("testUpdateSerial");
//        device.setDvcBuy(new Date());
//        device.setDvcExplan("testUpdateExplan");
//        device.setDvcCreated(timestamp);
//        device.setDvcUpdated(timestamp);
//        deviceRepository.updateDevice(device);
//
//    }
//    @Test
//    public void DeleteTest(){
//        deviceRepository.deleteDevice(3L);
//    }

//}
