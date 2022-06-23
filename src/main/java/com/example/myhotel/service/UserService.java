package com.example.myhotel.service;


import com.example.myhotel.dao.UserRepository;
import com.example.myhotel.entity.User;

import java.util.List;

public class UserService {

    private final UserRepository userRepository = UserRepository.getInstance();


    List<User> findAll() {
        return null;
//        return userRepository.findAll().stream()
//                .map()
    }


}



