package com.douzone.wehago.config;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service("twilio")
public class TwilioSmsSender implements SMsSender{

    private static final Logger LOGGER = LoggerFactory.getLogger(TwilioSmsSender.class);
    private final TwilioConfig twilioConfig;
        @Autowired
        public TwilioSmsSender(TwilioConfig twilioConfig){
            this.twilioConfig = twilioConfig;
        }
        @Override
        public void sendSms(SmsRequest smsRequest){
            if(isPhoneNumberVaild(smsRequest.getPhoneNumber())) {
                twilioConfig.setTrialNumber("+14702062651");
                System.out.println("이것은" + twilioConfig.getTrialNumber());
                PhoneNumber to = new PhoneNumber(smsRequest.getPhoneNumber());
                PhoneNumber from = new PhoneNumber(twilioConfig.getTrialNumber());
                String message = smsRequest.getMessage();
                MessageCreator creator = Message.creator(to, from, message);
                creator.create();
                LOGGER.info("Send sms {}" + smsRequest);
            }else {
                throw new IllegalArgumentException(
                        "Phone number [" + smsRequest.getPhoneNumber() + "] is not a valid number");
            }
        }

    private boolean isPhoneNumberVaild(String phoneNumber) {
            // TODO : Implement phone number validator
            return  true;
    }
}
