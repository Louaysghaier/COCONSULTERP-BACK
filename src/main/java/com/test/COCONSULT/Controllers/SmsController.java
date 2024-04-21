package com.test.COCONSULT.Controllers;

import com.test.COCONSULT.Services.SMSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/SMS")
public class SmsController {

    @Autowired
    private SMSService smsService;

    @PostMapping("/send-sms")
    public ResponseEntity<String> sendSMS(@RequestParam String to, @RequestParam String message) {
        smsService.sendSms(to, message);
        return ResponseEntity.ok("SMS sent successfully.");
    }
}
