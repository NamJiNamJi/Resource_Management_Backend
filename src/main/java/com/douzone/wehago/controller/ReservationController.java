package com.douzone.wehago.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.douzone.wehago.common.Response;
import com.douzone.wehago.domain.Reservation;
import com.douzone.wehago.domain.User;
import com.douzone.wehago.dto.reservation.ReservationChartDTO;
import com.douzone.wehago.dto.reservation.ReservationPageResponseDTO;
import com.douzone.wehago.dto.reservation.ReservationDTO;
import com.douzone.wehago.security.UserDetailsImpl;
import com.douzone.wehago.service.ReservationService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Map;

@RestController
@Slf4j
@AllArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;


    @GetMapping("/api/reservationlist")
    public ResponseEntity<Object> reservationList(@RequestParam(defaultValue = "1") Integer pageNum,
                                                  @RequestParam(defaultValue = "3") Integer pageSize,
                                                  @AuthenticationPrincipal UserDetails userDetails) {
        ReservationPageResponseDTO reservationPageResponseDTO = reservationService.reservationList(pageNum, pageSize, userDetails);
        Response response = new Response(HttpStatus.OK, "나의 예약 조회 성공", reservationPageResponseDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 예약 등록
    @PostMapping("/api/reservation")
    public ResponseEntity<?> registerEvent(@RequestBody ReservationDTO reservationDTO,
                                           @AuthenticationPrincipal UserDetails userDetails) {
        System.out.println(reservationDTO.getRsvNum());
        reservationService.reservationEvent(reservationDTO, userDetails);

        return null;
    }
    @GetMapping("/api/reservationcount/selectAll")
    public ResponseEntity<Map<Integer, Integer>> getMonthlyReservationCounts(@AuthenticationPrincipal UserDetails userDetails) {
        User user =  ((UserDetailsImpl) userDetails).getUser();
        Integer copSeq = user.getCopSeq();
        Map<Integer, Integer> monthlyCounts = reservationService.getMonthlyReservationCounts(copSeq);

        return ResponseEntity.ok(monthlyCounts);
    }
    @GetMapping("/api/reservationcount/car")
    public ResponseEntity<Map<Integer, Integer>> getMonthlyReservationCountsCar(@AuthenticationPrincipal UserDetails userDetails) {
        ReservationChartDTO reservationChartDTO = new ReservationChartDTO();
        User user =  ((UserDetailsImpl) userDetails).getUser();
        reservationChartDTO.setCopSeq(user.getCopSeq());
        Integer rsvNum = 0;
        reservationChartDTO.setRsvNum(rsvNum);
        Map<Integer, Integer> monthlyCounts = reservationService.getMonthlyReservationCountsCar(reservationChartDTO);

        return ResponseEntity.ok(monthlyCounts);
    }
    @GetMapping("/api/reservationcount/device")
    public ResponseEntity<Map<Integer, Integer>> getMonthlyReservationCountsDevice(@AuthenticationPrincipal UserDetails userDetails) {
        ReservationChartDTO reservationChartDTO = new ReservationChartDTO();
        User user =  ((UserDetailsImpl) userDetails).getUser();
        reservationChartDTO.setCopSeq(user.getCopSeq());
        Integer rsvNum = 1;
        reservationChartDTO.setRsvNum(rsvNum);
        Map<Integer, Integer> monthlyCounts = reservationService.getMonthlyReservationCountsDevice(reservationChartDTO);

        return ResponseEntity.ok(monthlyCounts);
    }
    @GetMapping("/api/reservationcount/space")
    public ResponseEntity<Map<Integer, Integer>> getMonthlyReservationCountsSpace(@AuthenticationPrincipal UserDetails userDetails) {
        ReservationChartDTO reservationChartDTO = new ReservationChartDTO();
        User user =  ((UserDetailsImpl) userDetails).getUser();
        reservationChartDTO.setCopSeq(user.getCopSeq());
        Integer rsvNum = 2;
        reservationChartDTO.setRsvNum(rsvNum);
        Map<Integer, Integer> monthlyCounts = reservationService.getMonthlyReservationCountsSpace(reservationChartDTO);

        return ResponseEntity.ok(monthlyCounts);
    }

}