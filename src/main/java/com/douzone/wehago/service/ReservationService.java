package com.douzone.wehago.service;


import com.douzone.wehago.domain.Reservation;
import com.douzone.wehago.dto.reservation.ReservationPageResponseDTO;
import com.douzone.wehago.dto.reservation.ResponseReservationDTO;
import com.douzone.wehago.repository.ReservationRepository;
import com.douzone.wehago.security.UserDetailsImpl;
import com.github.pagehelper.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import com.douzone.wehago.domain.User;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService{
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




}
