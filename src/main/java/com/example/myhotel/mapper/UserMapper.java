package com.example.myhotel.mapper;

import com.example.myhotel.dto.UserDto;
import com.example.myhotel.entity.User;
import com.example.myhotel.entity.enums.Role;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMapper implements Mapper<UserDto, User> {

    private static final UserMapper INSTANCE = new UserMapper();

    public static UserMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public User mapFrom(UserDto object) {
        return User.builder()
                .firstName(object.getFirstName())
                .lastName(object.getLastName())
                .email(object.getEmail())
                .login(object.getLogin())
                .phoneNumber(object.getPhoneNumber())
                .password(object.getPassword()) // TODO Password Encryption and Decryption
                .role(Role.ROLE_USER)
//                .roleId(Role.valueOf(object.getRole()))
//                .roleId()

                .build();

    }


}
