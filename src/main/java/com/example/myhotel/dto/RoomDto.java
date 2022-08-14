package com.example.myhotel.dto;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class RoomDto {
    Integer id;
    Integer userId;
    String name;
    Short maxPerson;
    boolean isOrdered;
    Integer hotelId;
    Integer number;
}
