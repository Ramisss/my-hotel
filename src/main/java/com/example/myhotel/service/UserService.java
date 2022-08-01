package com.example.myhotel.service;


import com.example.myhotel.dao.impl.UserDao;
import com.example.myhotel.dto.UserDto;
import com.example.myhotel.entity.User;
import com.example.myhotel.exeption.DaoException;
import com.example.myhotel.mapper.UserMapper;
import com.example.myhotel.validation.RegisterValidation;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserService {

    public static final Logger logger = LogManager.getLogger();
    private final UserDao userDao = UserDao.getInstance();
    public final UserMapper userMapper = UserMapper.getInstance();

    private static final UserService INSTANCE = new UserService();


    public static UserService getInstance() {
        return INSTANCE;
    }

    public boolean create(UserDto userDto) throws DaoException {
        if (!validatePhoneAndEmail(userDto.getEmail(), userDto.getPhoneNumber())) {
            logger.log(Level.ERROR, "Please enter correct email or phone number");
            System.out.println("Please enter correct email or phone number");
            return false;
        } else if (!checkUserByEmail(userDto.getEmail())) {
            logger.log(Level.INFO, userDto.getEmail() + " is exists. Please SIGN IN");
            System.out.println(userDto.getEmail() + " is exists. Please SIGN IN");
            return false;
        }
        User user = userMapper.mapFrom(userDto);
        User savedUserToDB = userDao.save(user);
        System.out.println(savedUserToDB.getId());

        return true;
    }

//    public boolean authEmailAndPassword(String email, String password){
//        if (validatePhoneAndEmail())
//    }

    public boolean validatePhoneAndEmail(String email, String phone) {
        RegisterValidation registerValidation = new RegisterValidation();
        if (registerValidation.checkEmailValidation(email) &&
                registerValidation.checkPhoneNumber(phone))
            return true;
        return false;
    }

    public boolean checkUserByEmail(String email) throws DaoException {
        Optional<User> optionalUser = userDao.findByEmail(email);
        if (!optionalUser.isEmpty()) {
            return true;
        }
        return false;
    }


    public boolean authEmailAndPassword(String email, String password) throws DaoException {

        if (userDao.findByEmail(email).isPresent()) {
//            User user = userDao.findByEmail(email).orElseThrow(() -> new IllegalStateException("User do not get"));
            String passwordFromDB = userDao.findByEmail(email).get().getPassword();
            if (passwordFromDB.equals(password)) return true;
            return false;
        }

        return false;
    }


}



