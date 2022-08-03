package com.example.myhotel.dao.impl;

import com.example.myhotel.dao.Dao;
import com.example.myhotel.entity.Hotel;
import com.example.myhotel.exception.DaoException;
import com.example.myhotel.pool.ConnectionTestPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class HotelDao implements Dao<Integer, Hotel> {
    public static final Logger logger = LogManager.getLogger();

    public static final HotelDao INSTANCE = new HotelDao();

    private HotelDao() {
    }

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

    @Override
    public Hotel save(Hotel entity) throws DaoException {
        try (Connection connection = ConnectionTestPool.get();
             PreparedStatement prepareStatement = connection.prepareStatement(SAVE_SQL);

        ) {
            prepareStatement.setString(1, entity.getName());
            prepareStatement.setString(1, entity.getAddress());
            prepareStatement.setString(1, entity.getCountry());
            prepareStatement.setString(1, entity.getPhoneNumber());
            prepareStatement.setString(1, entity.getEmail());

            prepareStatement.executeUpdate();

            ResultSet generatedKeys = prepareStatement.getGeneratedKeys();
            generatedKeys.next();

            entity.setId();


        } catch (SQLException sqlException) {
            logger.log(Level.ERROR, "DRIVER NOT LOADED", sqlException);
            throw new DaoException(sqlException);
        }
        return null;
    }


    @Override
    public List<Hotel> findAll() throws DaoException {
        return null;
    }

    @Override
    public Optional<Hotel> findById(Integer id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public boolean delete(Integer id) throws DaoException {
        return false;
    }

    @Override
    public void update(Hotel entity) throws DaoException {

    }


}
