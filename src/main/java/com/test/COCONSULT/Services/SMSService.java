package com.test.COCONSULT.Services;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;

@Service
public class SMSService {

    public static final String ACCOUNT_SID = "AC76add91e6b5de53e86ad317ebedcc246";
    public static final String AUTH_TOKEN = "b6acdab9ab349315f381ed4f95dfe586";

    public void sendSms(String numeroDestinataire, String messageBody) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                        new PhoneNumber("+21653072028"),
                        new PhoneNumber("+15169793002"),
                        "You've been Successfully added to the project ")
                .create();

        System.out.println(message.getSid());
    }
}
