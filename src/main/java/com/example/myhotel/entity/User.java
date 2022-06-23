package com.example.myhotel.entity;


import com.example.myhotel.entity.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class User {
    private Integer id;
    private String firstName;
    private String lastName;
    private String password;
    private String phoneNumber;
    private String email;
    private String login;
    private Role roleId;

}
