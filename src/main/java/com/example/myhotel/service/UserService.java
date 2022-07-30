package com.example.myhotel.service;


import com.example.myhotel.dao.impl.UserDao;
import com.example.myhotel.dto.UserDto;
import com.example.myhotel.entity.User;
import com.example.myhotel.exeption.DaoException;
import com.example.myhotel.mapper.UserMapper;
import com.example.myhotel.validation.RegisterValidation;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserService {
    private final UserDao userDao = UserDao.getInstance();
    public final UserMapper userMapper = UserMapper.getInstance();

    private static final UserService INSTANCE = new UserService();


    public static UserService getInstance() {
        return INSTANCE;
    }

    public Integer create(UserDto userDto) throws DaoException {
        if (!validatePhoneAndEmail(userDto)) {
            System.out.println("Please enter correct email or phone number");
        } else if (!checkUserByEmail(userDto.getEmail())) {
            System.out.println(userDto.getEmail() + " is exists. Please SIGN IN");
        }
        User user = userMapper.mapFrom(userDto);
        User savedUserToDB = userDao.save(user);
        System.out.println(savedUserToDB.getId());

        return savedUserToDB.getId();
    }

//    public boolean authEmailAndPassword(String email, String password){
//        if (validatePhoneAndEmail())
//    }

    public boolean validatePhoneAndEmail(UserDto userDto) {
        RegisterValidation registerValidation = new RegisterValidation();
        if (registerValidation.checkEmailValidation(userDto.getEmail()) &&
                registerValidation.checkPhoneNumber(userDto.getPhoneNumber()))
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

               User user = userDao.findByEmail(email).orElseThrow(() -> new IllegalStateException("User do not get"));
               if (user.getPassword().equals(password)) return true;
               return false;
           }

        return false;
    }


}



