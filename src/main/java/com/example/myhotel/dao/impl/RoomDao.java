package com.example.myhotel.dao.impl;

import com.example.myhotel.dao.Dao;
import com.example.myhotel.entity.Room;
import com.example.myhotel.exception.DaoException;
import com.example.myhotel.pool.ConnectionTestPool;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RoomDao implements Dao<Integer, Room> {
    public static final Logger logger = LogManager.getLogger();

    public static final RoomDao INSTANCE = new RoomDao();

    private static final String SAVE_SQL = "insert into " +
            "room(user_id," +
            " name, " +
            "max_person," +
            " hotel_id," +
            " is_ordered)" +
            " VALUES(?,?,?,?,?) ";

    private static final String FIND_ALL_SQL = "select " +
            "id," +
            " user_id, " +
            "name," +
            " max_person, " +
            "hotel_id, " +
            "is_ordered from room";

    public static final String FIND_BY_ID_SQL = "select " +
            "id," +
            " user_id," +
            " name," +
            " max_person," +
            " hotel_id," +
            " is_ordered from room;";


    public static RoomDao getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Room> findAll() throws DaoException {
        Connection connection = ConnectionTestPool.get();
        try {
            PreparedStatement prepareStatement = connection.prepareStatement(FIND_ALL_SQL);
            ResultSet resultSet = prepareStatement.executeQuery();
            List<Room> roomList = new ArrayList<>();
            while (resultSet.next()) {
                roomList.add(roomBuilder(resultSet));
            }
            return roomList;

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private Room roomBuilder(ResultSet resultSet) throws SQLException {
        return new Room(
                resultSet.getInt("id"),
                resultSet.getInt("user_id"),
                resultSet.getString("name"),
                resultSet.getShort("max_person"),
                resultSet.getInt("hotel_id"),
                resultSet.getBoolean("is_ordered")
        );
    }

    @Override
    public Optional<Room> findById(Integer id) throws DaoException {
        try (Connection connection = ConnectionTestPool.get();
             PreparedStatement prepareStatement = connection.prepareStatement(FIND_BY_ID_SQL);
        ) {
            prepareStatement.setInt(1, id);
            ResultSet resultSet = prepareStatement.executeQuery();
            Room room;
            while (resultSet.next()) {
                room = roomBuilder(resultSet);
                return Optional.of(room);
            }
        } catch (SQLException sqlException) {
            logger.log(Level.ERROR, "ERROR in RoomDao method findById");
            throw new DaoException(sqlException);
        }


        return Optional.empty();
    }

    @Override
    public boolean delete(Integer id) throws DaoException {
        return false;
    }

    @Override
    public void update(Room entity) throws DaoException {

    }

    @Override
    public Room save(Room entity) throws DaoException {
        try {
            Connection connection = ConnectionTestPool.get();
            var prepareStatement = connection.prepareStatement(SAVE_SQL);

            if (entity.getHotelId() == null || entity.getUserId() == null) {
                logger.log(Level.ERROR, "User Id or Hotel Id is null (RoomDao-method save)");
                throw new DaoException("UserId or Hotel Id is null");
            }
            prepareStatement.setInt(1, entity.getUserId());
            prepareStatement.setString(2, entity.getName());
            prepareStatement.setShort(3, entity.getMaxPerson());
            prepareStatement.setInt(4, entity.getHotelId());
            prepareStatement.setBoolean(5, entity.getIsOrdered());

            prepareStatement.executeUpdate();
            ResultSet generatedKeys = prepareStatement.getGeneratedKeys();
            if (generatedKeys.next())
                entity.setId(generatedKeys.getObject("id", Integer.class));// TODO not work this code
            return entity;
        } catch (SQLException e) {
            logger.log(Level.ERROR, "RoomDao error");
            throw new DaoException(e);
        }
    }
}
