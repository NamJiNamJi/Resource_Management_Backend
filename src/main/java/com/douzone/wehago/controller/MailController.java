package com.douzone.wehago.controller;

import com.douzone.wehago.common.Response;
import com.douzone.wehago.dto.MailDTO;
import com.douzone.wehago.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MailController {
    private final MailService mailService;

    @PostMapping("/api/employee/mail")
    public ResponseEntity<Object> mailSend(@RequestBody MailDTO mailDTO) {
        mailService.mailSend(mailDTO);

        Response response = new Response(HttpStatus.OK, mailDTO.getEmpEmail() + "로 메일 발송 성공", null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
