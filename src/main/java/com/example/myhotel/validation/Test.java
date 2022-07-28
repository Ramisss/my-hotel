package com.example.myhotel.validation;

import com.example.myhotel.entity.enums.Role;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        System.out.println(Role.valueOf("ROLE_USER").ordinal());

        System.out.println(Role.ROLE_USER);


        RegisterValidation registerValidation = new RegisterValidation();

        String email = "s_ramis@inbo.ru";
        String phone = "+998 (00) 393-09-34";
        String phone2 = "+998993930934";

        System.out.println(registerValidation.checkEmailValidation(email));
        System.out.println(registerValidation.checkPhoneNumber(phone2));


        System.out.println(Role.ROLE_ADMIN.ordinal());

        System.out.println(Role.values());

    }
}
