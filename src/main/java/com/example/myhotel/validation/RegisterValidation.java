package com.example.myhotel.validation;

import java.util.regex.Pattern;

public class RegisterValidation {

    private static final String EMAIL_REGEX = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$";
    private static final String PHONE_REGAEX = "^\\+\\d{12}$";

    public boolean checkEmailValidation(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        return pattern.matcher(email).matches();
    }

    public boolean checkPhoneNumber(String phone){
        Pattern pattern = Pattern.compile(PHONE_REGAEX);
        return pattern.matcher(phone).matches();
    }

}
