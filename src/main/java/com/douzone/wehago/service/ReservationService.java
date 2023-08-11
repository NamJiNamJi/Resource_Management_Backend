package com.douzone.wehago.service;

import com.douzone.wehago.common.Response;
import com.douzone.wehago.domain.Reservation;
import com.douzone.wehago.domain.User;
import com.douzone.wehago.dto.reservation.ReservationDTO;
import com.douzone.wehago.repository.ReservationRepository;
import com.douzone.wehago.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ModelMapper modelMapper;
    private final ReservationRepository reservationRepository;

    @Transactional
    public Response reservationEvent(ReservationDTO reservationDTO, UserDetails userDetails) {

        User user = ((UserDetailsImpl) userDetails).getUser(); // 토큰이 유효한 토큰이라면 유저 정보를 가지고 온다.

        Map<String, Object> message = new HashMap<>();

        if (user == null) {
            String errorMessage = "토큰이 만료되었거나, 회원정보를 찾을 수 없습니다.";
            return Response.builder()
                    .status(HttpStatus.UNAUTHORIZED)
                    .message(errorMessage)
                    .build();
        }

        System.out.println("========= start ===========");
        System.out.println(reservationDTO.getCopSeq());
        System.out.println(reservationDTO.getRsvDetail());
        System.out.println(reservationDTO.getRsvId());
        System.out.println(reservationDTO.getRsvName());
        System.out.println(reservationDTO.getRsvTitle());
        System.out.println(reservationDTO.getRsvParti());
        System.out.println(reservationDTO.getRsvExplain());
        System.out.println(reservationDTO.getRsvStart());
        System.out.println(reservationDTO.getRsvEnd());
        System.out.println("=========  end  ===========");

        Reservation reservation = modelMapper.map(reservationDTO, Reservation.class);

        reservationRepository.registrationEvent(reservation);

        return null;
    }
}
