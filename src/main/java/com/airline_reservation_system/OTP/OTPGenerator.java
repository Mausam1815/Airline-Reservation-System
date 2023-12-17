package com.airline_reservation_system.OTP;

import java.util.Random;

public class OTPGenerator {
    public static String generateOtp() {
        int otpLength = 4;
        String allowedChar = "0123456789";
        StringBuilder otp = new StringBuilder(otpLength);
        Random random = new Random();
        for(int i=0; i<otpLength; i++) {
            int randomIdx = random.nextInt(allowedChar.length());
            char randomChar = allowedChar.charAt(randomIdx);
            otp.append(randomChar);
        }
        return otp.toString();
    }
}
