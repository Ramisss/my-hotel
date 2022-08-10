package com.example.myhotel.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Builder
public class Room {
    private Integer id;
    private Integer userId;
    private String name;
    private Short maxPerson;
    private Integer hotelId;
    private boolean isOrdered;

}
