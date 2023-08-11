package com.douzone.wehago.service;


import com.douzone.wehago.common.Response;
import com.douzone.wehago.domain.Reservation;
import com.douzone.wehago.dto.reservation.ReservationDTO;
import com.douzone.wehago.dto.reservation.ReservationPageResponseDTO;
import com.douzone.wehago.dto.reservation.ResponseReservationDTO;
import com.douzone.wehago.repository.ReservationRepository;
import com.douzone.wehago.security.UserDetailsImpl;
import com.github.pagehelper.Page;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import com.douzone.wehago.domain.User;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReservationService{

    private final ModelMapper modelMapper;
    private final ReservationRepository reservationRepository;
    protected static final String APP_TYPE_URL_ENCODED = "application/x-www-form-urlencoded;charset=UTF-8";
    protected static final String APP_TYPE_JSON = "application/json;charset=UTF-8";
    @Transactional(readOnly = true)
    public ReservationPageResponseDTO reservationList(int pageNo , int pageSize, UserDetails userDetails){
        User user = ((UserDetailsImpl) userDetails).getUser();

        String rsvId = user.getUserId();


        List<Reservation> list = reservationRepository.reservationList(pageNo,pageSize,rsvId);
        Object total =((Page) list).getPages();
        List<ResponseReservationDTO> responseReservationDTOList = new ArrayList<>();

        for(Reservation reservation : list){
            if(reservation.getRsvState()){
                responseReservationDTOList.add(getResponseReservationDTO(reservation));
            }
        }
        return ReservationPageResponseDTO.builder()
                .list(responseReservationDTOList)
                .total(total)
                .build();
    }

    private ResponseReservationDTO getResponseReservationDTO (Reservation reservation){
        return ResponseReservationDTO.builder()
                .rsvSeq(reservation.getRsvSeq())
                .copSeq(reservation.getCopSeq())
                .rsvDetail(reservation.getRsvDetail())
                .rsvId(reservation.getRsvId())
                .rsvName(reservation.getRsvName())
                .rsvExplain(reservation.getRsvExplain())
                .rsvParti(reservation.getRsvParti())
                .rsvTitle(reservation.getRsvTitle())
                .rsvStart(reservation.getRsvStart())
                .rsvEnd(reservation.getRsvEnd())
                .rsvState(reservation.getRsvState())
                .build();
    }

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
