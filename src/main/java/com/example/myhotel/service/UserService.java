package com.example.myhotel.service;

import com.example.myhotel.dto.UserDto;
import com.example.myhotel.exception.ServiceException;

public interface UserService {

    boolean create(UserDto userDto) throws ServiceException;

    boolean authEmailAndPassword (String email, String password) throws ServiceException;

    boolean checkUserByEmail (String email) throws ServiceException;
}
