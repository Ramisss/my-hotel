package com.example.myhotel.service.impl;

import com.example.myhotel.dao.impl.HotelDao;
import com.example.myhotel.dto.HotelDto;
import com.example.myhotel.entity.Hotel;
import com.example.myhotel.exception.DaoException;
import com.example.myhotel.exception.ServiceException;
import com.example.myhotel.mapper.HotelMapper;
import com.example.myhotel.service.HotelService;
import com.example.myhotel.validation.RegisterValidation;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE) // create private constructor
public class HotelServiceImpl implements HotelService {
    public static final Logger logger = LogManager.getLogger();
    private final HotelDao hotelDao = HotelDao.getInstance();
    private final HotelMapper hotelMapper = HotelMapper.getInstance();

    public static final HotelServiceImpl INSTANCE = new HotelServiceImpl();

    public static HotelServiceImpl getInstance() {
        return INSTANCE;
    }


    @Override
    public Hotel add(HotelDto hotelDto) throws ServiceException {
        Hotel hotel = null;
        if (validatePhoneAndEmail(hotelDto.getEmail(), hotelDto.getPhoneNumber())) {

            hotel = hotelMapper.mapFrom(hotelDto);
            try {
                hotel = hotelDao.save(hotel);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return hotel;
    }

    @Override
    public boolean update(HotelDto hotelDto) throws ServiceException {
        return false;
    }

    @Override
    public boolean delete(HotelDto hotelDto) throws ServiceException {
        return false;
    }

    public boolean validatePhoneAndEmail(String email, String phone) {
        RegisterValidation registerValidation = new RegisterValidation();
        if (registerValidation.checkEmailValidation(email) &&
                registerValidation.checkPhoneNumber(phone))
            return true;
        return false;
    }

    public void getHotel() {

    }

    public Optional<Hotel> findById(Integer hotelId) throws ServiceException {
        try {
            return hotelDao.findById(hotelId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Hotel was not found by this ID ==" + hotelId, e);
            throw new ServiceException(e);
        }
    }
}
