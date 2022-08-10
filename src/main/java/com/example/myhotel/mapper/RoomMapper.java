package com.example.myhotel.mapper;

import com.example.myhotel.dto.RoomDto;
import com.example.myhotel.entity.Room;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RoomMapper implements Mapper<RoomDto, Room>{
    public static final RoomMapper INSTANCE = new RoomMapper();

    public static RoomMapper getInstance(){
        return INSTANCE;
    }

    @Override
    public Room mapFrom(RoomDto object) {
        return Room.builder()
                .userId(object.getUserId())
                .name(object.getName())
                .maxPerson(object.getMaxPerson())
                .hotelId(object.getHotelId())
                .isOrdered(object.isOrdered())
                .build();
    }
}
