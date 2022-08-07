package com.example.myhotel.entity.type;

import java.util.Arrays;

public enum Role {
    ROLE_ADMIN,
    ROLE_USER;

    public static Role find(Integer id) {
        return Arrays.stream(values())
                .filter(it -> it.ordinal()==(id))
                .findFirst().get();
    }

}
