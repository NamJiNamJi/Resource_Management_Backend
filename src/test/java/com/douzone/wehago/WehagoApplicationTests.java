package com.douzone.wehago;

import com.douzone.wehago.dto.DeviceDTO;
import com.douzone.wehago.dto.RoomDTO;
import com.douzone.wehago.repository.DeviceRepository;
import com.douzone.wehago.repository.RoomRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@SpringBootTest

class WehagoApplicationTests {

//     DeviceTestCRUD
//    private final DeviceRepository deviceRepository;
//
//    @Autowired
//    WehagoApplicationTests(DeviceRepository deviceRepository) {
//        this.deviceRepository = deviceRepository;
//    }
      private final RoomRepository roomRepository;

      @Autowired
     WehagoApplicationTests(RoomRepository roomRepository){
          this.roomRepository = roomRepository;
      }

      @Test
      public void  dbInsertTest(){
          RoomDTO room = new RoomDTO();
          Timestamp timestamp = new Timestamp(System.currentTimeMillis());

          room.setSpcName("공간 명");
          room.setSpcCap(3);
          room.setSpcExplan("공간설명");
          room.setSpcCreated(timestamp);
          room.setSpcUpdated(timestamp);
          roomRepository.insertRoom(room);
      }

      @Test
      public void dbselectAllTest() {
          List<RoomDTO> selectAll = roomRepository.selectAllRooms();
          for (RoomDTO room : selectAll) {
            System.out.println("room = " + room);
         }
      }
      @Test
      public void dbUpdateTest(){
          RoomDTO room = new RoomDTO();
          Timestamp timestamp = new Timestamp(System.currentTimeMillis());
          room.setSpcSeq(1L);
          room.setSpcName("김동민");
          room.setSpcCap(2);
          room.setSpcExplan("수정본");
          room.setSpcCreated(timestamp);
          room.setSpcUpdated(timestamp);
          roomRepository.updateRoom(room);
      }
      @Test
      public void dbDeleteTest(){
          roomRepository.deleteRoom(2L);
      }
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

}
