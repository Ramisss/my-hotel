package com.example.myhotel.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

@Value
@Builder
public class UserDto {
     String firstName;
     String lastName;
     String password;
     String phoneNumber;
     String email;
     String login;
     int roleId;

}
