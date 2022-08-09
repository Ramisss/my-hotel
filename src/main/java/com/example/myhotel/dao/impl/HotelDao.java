package com.example.myhotel.dao.impl;

import com.example.myhotel.dao.Dao;
import com.example.myhotel.entity.Hotel;
import com.example.myhotel.exception.DaoException;
import com.example.myhotel.pool.ConnectionTestPool;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HotelDao implements Dao<Integer, Hotel> {
    public static final Logger logger = LogManager.getLogger();

    public static final HotelDao INSTANCE = new HotelDao();


    public static HotelDao getInstance() {
        return INSTANCE;
    }

    public static final String SAVE_SQL = "insert into hotel" +
            " (name," +
            " address, " +
            "country," +
            " phone_number," +
            " e_mail) " +
            "VALUES(?,?,?,?,?) ";

    public static final String FIND_BY_ID_SQL = "select " +
            "name," +
            "address," +
            "country," +
            "phone_number," +
            "e_mail" +
            " from hotel where id =?";

    @Override
    public Hotel save(Hotel entity) throws DaoException {
        try (Connection connection = ConnectionTestPool.get();
             PreparedStatement prepareStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS);
        ) {
            prepareStatement.setString(1, entity.getName());
            prepareStatement.setString(2, entity.getAddress());
            prepareStatement.setString(3, entity.getCountry());
            prepareStatement.setString(4, entity.getPhoneNumber());
            prepareStatement.setString(5, entity.getEmail());

            prepareStatement.executeUpdate();

            ResultSet generatedKeys = prepareStatement.getGeneratedKeys();
            generatedKeys.next();

            entity.setId(generatedKeys.getObject("id", Integer.class));
            return entity;


        } catch (SQLException sqlException) {
            logger.log(Level.ERROR, "DRIVER NOT LOADED", sqlException);
            throw new DaoException(sqlException);
        }
    }


    @Override
    public List<Hotel> findAll() throws DaoException {
        return null;
    }

    @Override
    public Optional<Hotel> findById(Integer id) throws DaoException {
        return Optional.empty();
    }

    public Optional<Hotel> findById(int id) throws DaoException {
        Connection connection = ConnectionTestPool.get();
        try {
            PreparedStatement prepareStatement = connection.prepareStatement(FIND_BY_ID_SQL);
            prepareStatement.setInt(1, id);
            ResultSet resultSet = prepareStatement.executeQuery();
            Hotel hotel = new Hotel();

            while (resultSet.next()) {
                hotel = buildHotel(resultSet);
                return Optional.of(hotel);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "hotelDao error (findById-method)" + e);
            throw new DaoException(e);
        }

        return Optional.empty();
    }


    @Override
    public boolean delete(Integer id) throws DaoException {
        return false;
    }

    @Override
    public void update(Hotel entity) throws DaoException {

    }

    private Hotel buildHotel(ResultSet resultSet) throws SQLException {
        return new Hotel(
                resultSet.getObject("id", Integer.class),
                resultSet.getObject("name", String.class),
                resultSet.getObject("address", String.class),
                resultSet.getObject("country", String.class),
                resultSet.getObject("phone_number", String.class),
                resultSet.getObject("e_mail", String.class));
    }

}
