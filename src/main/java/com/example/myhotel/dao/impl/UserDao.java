package com.example.myhotel.dao.impl;


import com.example.myhotel.dao.Dao;
import com.example.myhotel.entity.User;
import com.example.myhotel.entity.enums.Role;
import com.example.myhotel.exeption.DaoException;
import com.example.myhotel.util.ConnectionPool;
import com.example.myhotel.util.ConnectionTestPool;
import lombok.SneakyThrows;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao implements Dao<Integer, User> {

    static Logger logger = LogManager.getLogger();

    private static final UserDao INSTANCE = new UserDao();

    private UserDao() {

    }

    public static UserDao getInstance() {
        return INSTANCE;
    }


    private static final String FIND_BY_EMAIL_SQL = "select first_name,last_name,phone_number,e_mail,login from users where e_mail = ?";

    private static final String FIND_ALL = "select first_name,last_name,phone_number,e_mail,login from users;";

    private static final String FIND_BY_ID = "select first_name,last_name,phone_number,e_mail,login from users where id = ?;";

    private static final String FIND_BY_LOGIN = "select * from users where login = ?;";

    private static final String FIND_BY_EMAIL_AND_PASSWORD = "select users.first_name,users.last_name,users.phone_number,users.id from " +
            "users where e_mail = ? and password = ?";

    public static final String SAVE_SQL =
            "INSERT into users(first_name, last_name, password, phone_number, e_mail, login, role_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

    @SneakyThrows
    public User findByEmailAndPassword(String email, String password) {
        User user = null;
        try (Connection connection = ConnectionTestPool.get();
//                var connection = ConnectionPool.getInstance().getConnection();
             var prepareStatement = connection.prepareStatement(FIND_BY_EMAIL_AND_PASSWORD)) {

            prepareStatement.setObject(1, email);
            prepareStatement.setObject(2, password);

            ResultSet resultSet = prepareStatement.executeQuery();

            while (resultSet.next()) {
                user = buildUser(resultSet);
            }
        }
        return user;

    }

    @Override
    public List<User> findAll() throws DaoException {
        try (Connection connection = ConnectionTestPool.get();
//                var connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement(FIND_ALL)

        ) {
            ResultSet resultSet = prepareStatement.executeQuery();

            List<User> userList = new ArrayList<>();
            while (resultSet.next()) {
                userList.add(buildUser(resultSet));
            }

            return userList;

        } catch (SQLException sqlException) {
            logger.log(Level.ERROR, "SQL Exception findAll method USERDAO");
            throw new DaoException("UserRep findAll DaoExp");

        }

    }


    @Override
    public Optional<User> findById(Integer id) throws SQLException {

        ResultSet resultSet;
        try (Connection connection = ConnectionTestPool.get();
//        var open = ConnectionPool.getInstance().getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement(FIND_BY_ID)) {
            prepareStatement.setInt(1, id);
            resultSet = prepareStatement.executeQuery();
        }
        User user = null;

        while (resultSet.next()) {
            user = buildUser(resultSet);
        }

        return Optional.of(user);
        // TODO Optional.of() is it CORRECT?
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public void update(User entity) {

    }

    @Override
    @SneakyThrows
    public User save(User entity) {
        try (
//                Connection connection = ConnectionTestPool.get();
                Connection connection = ConnectionPool.getInstance().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setObject(1, entity.getFirstName());
            preparedStatement.setObject(2, entity.getLastName());
            preparedStatement.setObject(3, entity.getPassword());
            preparedStatement.setObject(4, entity.getPhoneNumber());
            preparedStatement.setObject(5, entity.getEmail());
            preparedStatement.setObject(6, entity.getLogin());
            preparedStatement.setObject(7, entity.getRole().ordinal()); // TODO error

            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            entity.setId(generatedKeys.getObject("id", Integer.class));

            return entity;
        }

    }

    public Optional<User> findByEmail(String email) {
        Connection connection = ConnectionPool.getInstance().getConnection();
//        Connection connection = ConnectionTestPool.get();
        ResultSet resultSet;
        User user = null;
        try (var prepareStatement = connection.prepareStatement(FIND_BY_EMAIL_SQL)) {
            prepareStatement.setString(1, email);
            resultSet = prepareStatement.executeQuery();

            while (resultSet.next()) {
                user = buildUser(resultSet);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return Optional.ofNullable(user);

    }

    private User buildUser(ResultSet resultSet) throws SQLException {
        return new User(
                resultSet.getObject("id", Integer.class),
                resultSet.getObject("first_name", String.class),
                resultSet.getObject("last_name", String.class),
                resultSet.getObject("password", String.class),
                resultSet.getObject("phone_number", String.class),
                resultSet.getObject("e_mail", String.class),// TODO email or e_mail ??
                resultSet.getObject("login", String.class),
                Role.valueOf(resultSet.getObject("role_id", String.class))); // TODO check
    }

    public User findByLogin(String login) throws SQLException {
        User user = null;
        try (Connection connection = ConnectionTestPool.get();
//                Connection open = ConnectionManager.open()
        ) {
            PreparedStatement prepareStatement = connection.prepareStatement(FIND_BY_LOGIN);

            prepareStatement.setString(1, login);

            ResultSet resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                user = buildUser(resultSet);
            }
        }

        return user;
    }
}
