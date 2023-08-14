package com.douzone.wehago.service;

import com.douzone.wehago.config.SMsSender;
import com.douzone.wehago.config.SmsRequest;
import com.douzone.wehago.config.TwilioSmsSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@org.springframework.stereotype.Service
public class SmsService {
    private final SMsSender sendSender;

    @Autowired
    public SmsService(@Qualifier("twilio") TwilioSmsSender twilioSmsSender){
        this.sendSender = twilioSmsSender;
    }
    public void sendSms(SmsRequest smsRequest){
        sendSender.sendSms(smsRequest);
    }
}
