package com.example.myhotel.entity;

import lombok.Builder;
import lombok.Data;

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
