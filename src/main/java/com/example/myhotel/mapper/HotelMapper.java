package com.example.myhotel.mapper;

import com.example.myhotel.dto.HotelDto;
import com.example.myhotel.entity.Hotel;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE) //private constructor for singleton
public class HotelMapper implements Mapper<HotelDto, Hotel> {

    public static final HotelMapper INSTANCE = new HotelMapper();

    public static HotelMapper getInstance(){
        return INSTANCE;
    }

    @Override
    public Hotel mapFrom(HotelDto object) {
        return Hotel.builder()
                .name(object.getName())
                .address(object.getAddress())
                .country(object.getCountry())
                .phoneNumber(object.getPhoneNumber())
                .email(object.getEmail())
                .build();
    }
}
