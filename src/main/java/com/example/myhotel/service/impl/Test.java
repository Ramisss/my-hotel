package com.example.myhotel.service.impl;

import com.example.myhotel.dao.impl.UserDao;
import com.example.myhotel.entity.User;
import com.example.myhotel.entity.type.Role;
import com.example.myhotel.exception.DaoException;

import java.util.List;

public class Test {
    public static void main(String[] args) throws DaoException {
    final UserDao userDao = UserDao.getInstance();
        System.out.println("ROLE_ADMIN".equals(Role.ROLE_ADMIN.toString()));
        List<User> all = userDao.findAll();

    }

}
