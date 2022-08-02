package com.example.myhotel.service;


import com.example.myhotel.dao.impl.UserDao;
import com.example.myhotel.dto.UserDto;
import com.example.myhotel.entity.User;
import com.example.myhotel.exception.DaoException;
import com.example.myhotel.exception.ServiceException;
import com.example.myhotel.mapper.UserMapper;
import com.example.myhotel.validation.RegisterValidation;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserServiceImpl implements UserService {

    public static final Logger logger = LogManager.getLogger();
    private final UserDao userDao = UserDao.getInstance();
    public final UserMapper userMapper = UserMapper.getInstance();

    private static final UserServiceImpl INSTANCE = new UserServiceImpl();

    public static UserServiceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean create(UserDto userDto) throws ServiceException {
        if (!validatePhoneAndEmail(userDto.getEmail(), userDto.getPhoneNumber())) {
            logger.log(Level.ERROR, "Please enter correct email or phone number");
            return false;
        } else {
            if (!checkUserByEmail(userDto.getEmail())) {
                logger.log(Level.INFO, userDto.getEmail() + " is exists. Please SIGN IN");
                return false;
            }
        }
        User user = userMapper.mapFrom(userDto);
        User savedUserToDB = null;
        try {
            savedUserToDB = userDao.save(user);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        logger.log(Level.INFO, savedUserToDB.getId());

        return true;
    }

    public boolean validatePhoneAndEmail(String email, String phone) {
        RegisterValidation registerValidation = new RegisterValidation();
        if (registerValidation.checkEmailValidation(email) &&
                registerValidation.checkPhoneNumber(phone))
            return true;
        return false;
    }

    @Override
    public boolean checkUserByEmail(String email) throws ServiceException {
        Optional<User> optionalUser = null;
        try {
            optionalUser = userDao.findByEmail(email);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "error in UserServiceImpl method checkUserByEmail");
            throw new ServiceException(e);
        }
        return !optionalUser.isEmpty();
    }

    @Override
    public boolean authEmailAndPassword(String email, String password) throws ServiceException {
        try {
            if (userDao.findByEmail(email).isPresent()) {
                String passwordFromDB = userDao.findByEmail(email).get().getPassword();
                return passwordFromDB.equals(password);
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, "error in UserServiceImpl method authEmailAndPassword");
            throw new ServiceException(e);
        }
        return false;
    }


}



