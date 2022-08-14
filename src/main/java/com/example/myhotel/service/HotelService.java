package com.example.myhotel.service;

import com.example.myhotel.dto.HotelDto;
import com.example.myhotel.entity.Hotel;
import com.example.myhotel.exception.ServiceException;

import java.util.Optional;

public interface HotelService {

    Hotel add(HotelDto hotelDto) throws ServiceException;

    boolean update(HotelDto hotelDto) throws ServiceException;

    boolean delete(HotelDto hotelDto) throws ServiceException;

    Optional<Hotel> findById(Integer hotelId) throws ServiceException;
}
