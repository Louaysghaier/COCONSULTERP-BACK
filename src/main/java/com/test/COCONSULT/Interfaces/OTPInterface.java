package com.test.COCONSULT.Interfaces;

import com.test.COCONSULT.DTO.OTP;

import java.net.http.HttpTimeoutException;

public interface OTPInterface {
    OTP GenerateOTp( );
    Boolean VerifOTP(OTP otp) ;

    OTP ResendOTP(OTP existingOTP);
}
