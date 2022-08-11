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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

    public static RoomDao getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Room> findAll() throws DaoException {
        return null;
    }

    @Override
    public Optional<Room> findById(Integer id) throws DaoException {
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
            var prepareStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS);

            if (entity.getHotelId() == null || entity.getUserId() == null) {
                logger.log(Level.ERROR, "User Id or Hotel Id is null (RoomDao-method save)");
                throw new DaoException("UserId or Hotel Id is null");
            }

            prepareStatement.setInt(1, entity.getUserId());
            prepareStatement.setString(2, entity.getName());
            prepareStatement.setShort(3, entity.getMaxPerson());
            prepareStatement.setInt(4, entity.getHotelId());
            prepareStatement.setObject(5, entity.isOrdered());

            prepareStatement.executeUpdate();
            ResultSet generatedKeys = prepareStatement.getGeneratedKeys();
            generatedKeys.next();

//            ResultSet generatedKeys = prepareStatement.getGeneratedKeys();
//            generatedKeys.next();
//
            entity.setId(generatedKeys.getObject("id", Integer.class));
            return entity;

        } catch (SQLException e) {
            logger.log(Level.ERROR, "RoomDao error");
            throw new DaoException(e);
        }
    }
}
