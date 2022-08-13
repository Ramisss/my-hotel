package com.example.myhotel.service.impl;

import com.example.myhotel.dao.impl.RoomDao;
import com.example.myhotel.dto.RoomDto;
import com.example.myhotel.entity.Room;
import com.example.myhotel.exception.DaoException;
import com.example.myhotel.exception.ServiceException;
import com.example.myhotel.mapper.RoomMapper;
import com.example.myhotel.service.RoomService;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RoomServiceImpl implements RoomService {
    public static final Logger logger = LogManager.getLogger();
    public final RoomMapper roomMapper = RoomMapper.getInstance();
    public final RoomDao roomDao = RoomDao.getInstance();


    public static final RoomServiceImpl INSTANCE = new RoomServiceImpl();

    public static RoomServiceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public Room add(RoomDto roomDto) throws ServiceException {
        Room room = roomMapper.mapFrom(roomDto);
        try {
            room = roomDao.save(room);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "room add - RoomServiceImpl");
            throw new ServiceException(e);
        }
        return room;
    }

    @Override
    public boolean update(RoomDto roomDto) throws ServiceException {
        return false;
    }

    @Override
    public boolean delete(RoomDto roomDto) throws ServiceException {
        return false;
    }


    public List<Room> findAllUsers() throws ServiceException {
        try {
            List<Room> roomList;
            roomList = roomDao.findAll();
            if (roomList.isEmpty())
                return Collections.emptyList();
            return roomList;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
