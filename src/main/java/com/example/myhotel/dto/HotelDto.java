package com.example.myhotel.dto;


import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class HotelDto {
    String name;
    String address;
    String country;
    String phoneNumber;
    String email;
}
