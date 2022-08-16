package com.example.myhotel.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
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
