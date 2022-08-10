package com.example.myhotel.service;

import com.example.myhotel.dto.HotelDto;
import com.example.myhotel.dto.RoomDto;
import com.example.myhotel.entity.Hotel;
import com.example.myhotel.entity.Room;
import com.example.myhotel.exception.ServiceException;

public interface RoomService {
    Room add(RoomDto roomDto) throws ServiceException;

    boolean update(RoomDto roomDto) throws ServiceException;

    boolean delete(RoomDto roomDto) throws ServiceException;
}
