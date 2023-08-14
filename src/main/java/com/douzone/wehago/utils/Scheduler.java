package com.douzone.wehago.utils;

import com.douzone.wehago.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class Scheduler {

    private final ReservationService reservationService;

//    @Scheduled(cron = "0 0 0/1 * * *", zone = "Asia/Seoul") // 1시간마다 실행
    @Scheduled(fixedDelay = 1000 * 60 * 60) // 1시간마다 실행
    public void scheduledReservation() {
        System.out.println("\n");
        System.out.println("=======================================");
        System.out.println("[ScheduleService] : [예약 업데이트 실행] : [start]");
        reservationService.scheduledReservation();
        System.out.println("[ScheduleService] : [예약 업데이트 완료] : [end]");
        System.out.println("=======================================");
        System.out.println("\n");
    }


}
