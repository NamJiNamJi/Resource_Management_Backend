package com.douzone.wehago.controller;

import com.douzone.wehago.config.SMsSender;
import com.douzone.wehago.config.SmsRequest;
import com.douzone.wehago.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/api/v1/sms")
public class SmsController {

    private final SmsService service;

    @Autowired
    public SmsController(SmsService service){
        this.service = service;
    }
    @PostMapping
    public void sendSms(@Valid @RequestBody SmsRequest smsRequest){
        System.out.println("안녕하세요");
        System.out.println(smsRequest);
        service.sendSms(smsRequest);
    }
}
