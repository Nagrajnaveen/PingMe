package com.devnags.pingme.adapters.model;

public class PhoneOtp {

    private String number;
    private String otp;


    public PhoneOtp(String number, String otp) {
        this.number = number;
        this.otp = otp;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}
