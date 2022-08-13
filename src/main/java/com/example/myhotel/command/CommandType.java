package com.example.myhotel.command;

import com.example.myhotel.command.impl.DefaultCommand;
import com.example.myhotel.command.impl.admin.EditRoomCommand;
import com.example.myhotel.command.impl.SignInCommand;
import com.example.myhotel.command.impl.SignUpCommand;
import com.example.myhotel.command.impl.admin.*;

public enum CommandType {
    SIGN_UP(new SignUpCommand()),
    SIGN_IN(new SignInCommand()),
    //    ADMIN
    ADD_HOTEL(new AddHotelCommand()),
    ADD_ROOM(new AddRoomCommand()),
    FIND_ALL_USERS(new FindAllUsersCommand()),
    FIND_ALL_ROOMS(new FindAllRoomsCommand()),
    FIND_ALL_ORDERS(new FindAllOrdersCommand()),
    EDIT_ROOM(new EditRoomCommand()),
    DELETE_ROOM(new DeleteRoomCommand()),

    DEFAULT(new DefaultCommand());

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public static Command define(String commandStr) {
        CommandType current;

        if (commandStr == null) {
            current = CommandType.DEFAULT;
            return current.command;
        }
        try {
            current = CommandType.valueOf(commandStr.toUpperCase());
            return current.command;
        } catch (IllegalArgumentException e) {
            current = CommandType.DEFAULT;
            return current.command;
        }
    }
}
