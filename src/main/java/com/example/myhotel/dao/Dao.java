package com.example.myhotel.dao;

import com.example.myhotel.exeption.DaoException;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface Dao<K, T> {

    List<T> findAll() throws DaoException;

    Optional<T> findById(K id) throws SQLException;

    boolean delete(K id) throws DaoException;

    void update(T entity) throws DaoException;

    T save(T entity) throws DaoException;


}
