package com.example.myhotel.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Builder
public class Hotel {

    private Integer id;
    private String name;
    private String address;
    private String country;
    private String phoneNumber;
    private String email;


}
