package com.example.myhotel.dao.impl;


import com.example.myhotel.dao.Dao;
import com.example.myhotel.entity.User;
import com.example.myhotel.entity.type.Role;
import com.example.myhotel.exception.DaoException;
import com.example.myhotel.pool.ConnectionTestPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao implements Dao<Integer, User> {

    private static final Logger logger = LogManager.getLogger();

    private static final UserDao INSTANCE = new UserDao();

    private UserDao() {

    }

    public static UserDao getInstance() {
        return INSTANCE;
    }


    private static final String FIND_BY_EMAIL_SQL =
            "select" +
                    " id, " +
                    "first_name," +
                    "last_name," +
                    "password," +
                    "phone_number," +
                    "e_mail," +
                    "login," +
                    "role_id" +
                    " from users where e_mail = ?";

    private static final String FIND_ALL =
            "select " +
                    "first_name," +
                    "last_name," +
                    "phone_number," +
                    "e_mail," +
                    "login" +
                    " from users;";

    private static final String FIND_BY_ID =
            "select" +
                    " first_name," +
                    "last_name," +
                    "phone_number," +
                    "e_mail," +
                    "login" +
                    " from users where id = ?;";

    private static final String FIND_BY_LOGIN =
            "select " +
                    "id, " +
                    "first_name," +
                    "last_name," +
                    "password," +
                    "phone_number," +
                    "e_mail," +
                    "login," +
                    "role_id " +
                    "from users where login = ?;";

    private static final String FIND_BY_EMAIL_AND_PASSWORD =
            "select" +
                    " first_name," +
                    "last_name," +
                    "phone_number," +
                    "id" +
                    " from " +
                    "users where e_mail = ? and password = ?";

    public static final String SAVE_SQL =
            "INSERT into" +
                    " users(first_name, " +
                    "last_name," +
                    " password, " +
                    "phone_number," +
                    "e_mail," +
                    "login, " +
                    "role_id) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";

    public static final String GET_ROLE_BY_NAME_SQL = "select name from roles where name = ?";

    public User findByEmailAndPassword(String email, String password) throws DaoException {
        User user = null;
        try (
                Connection connection = ConnectionTestPool.get();
//                var connection = ConnectionPool.getInstance().getConnection();
                var prepareStatement = connection.prepareStatement(FIND_BY_EMAIL_AND_PASSWORD)
        ) {

            prepareStatement.setObject(1, email);
            prepareStatement.setObject(2, password);

            ResultSet resultSet = prepareStatement.executeQuery();

            while (resultSet.next()) {
                user = buildUser(resultSet);
            }
        } catch (SQLException sqlException) {
            throw new DaoException(sqlException);
        }
        return user;

    }

    @Override
    public List<User> findAll() throws DaoException {
        try (
                Connection connection = ConnectionTestPool.get();
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
    public Optional<User> findById(Integer id) throws DaoException {

        try (Connection connection = ConnectionTestPool.get();
//                var connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement(FIND_BY_ID)) {
            prepareStatement.setInt(1, id);
            ResultSet resultSet = prepareStatement.executeQuery();
            User user = null;
            while (resultSet.next()) {
                user = buildUser(resultSet);
            }
            return Optional.of(user);
        } catch (SQLException sqlException) {
            logger.log(Level.ERROR, "can not connect to database", sqlException);
            throw new DaoException(sqlException);
        }
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public User save(User entity) throws DaoException {
        try (
                Connection connection = ConnectionTestPool.get();
//                Connection connection = ConnectionPool.getInstance().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, entity.getFirstName());
            preparedStatement.setString(2, entity.getLastName());
            preparedStatement.setString(3, entity.getPassword());
            preparedStatement.setString(4, entity.getPhoneNumber());
            preparedStatement.setString(5, entity.getEmail());
            preparedStatement.setString(6, entity.getLogin());
            preparedStatement.setObject(7, entity.getRole().ordinal()); // TODO error

            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            entity.setId(generatedKeys.getObject("id", Integer.class));

            return entity;
        } catch (SQLException sqlException) {
            logger.log(Level.ERROR, "error in database", sqlException);
            throw new DaoException(sqlException);
        }

    }

    public Optional<User> findByEmail(String email) throws DaoException {
        User user = new User();
        try (
                Connection connection = ConnectionTestPool.get();
                var prepareStatement = connection.prepareStatement(FIND_BY_EMAIL_SQL)) {
            prepareStatement.setString(1, email);
            ResultSet resultSet = prepareStatement.executeQuery();

            while (resultSet.next()) {
                user = buildUser(resultSet);
                return Optional.of(user);
            }
        } catch (SQLException sqlException) {
            logger.log(Level.ERROR, "can not connect to database", sqlException);
            throw new DaoException(sqlException);
        }
        return Optional.empty();

    }

    //    TODO This method for ROLE_USER
    private User buildUser(ResultSet resultSet) throws SQLException {
        return new User(
                resultSet.getObject("id", Integer.class),
                resultSet.getObject("first_name", String.class),
                resultSet.getObject("last_name", String.class),
                resultSet.getObject("password", String.class),
                resultSet.getObject("phone_number", String.class),
                resultSet.getObject("e_mail", String.class),
                resultSet.getObject("login", String.class),
                Role.find(resultSet.getObject("role_id", Integer.class)));
//                resultSet.getObject("role_id",Integer.class);
//                Role.valueOf("ROLE_USER")); // TODO check
    }



    public User findByLogin(String login) throws DaoException {
        User user = null;
        try (
                Connection open = ConnectionTestPool.get();
//                Connection open = ConnectionPool.getInstance().getConnection();
                PreparedStatement prepareStatement = open.prepareStatement(FIND_BY_LOGIN);
        ) {
            prepareStatement.setString(1, login);

            ResultSet resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                user = buildUser(resultSet);
            }
        } catch (SQLException sqlException) {
            throw new DaoException(sqlException);
        }

        return user;
    }

    public Optional<Role> getRoleById(Integer id) {

        try (Connection connection = ConnectionTestPool.get();
             var prepareStatement = connection.prepareStatement(GET_ROLE_BY_NAME_SQL);
        ) {
            ResultSet resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {

            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }
}
