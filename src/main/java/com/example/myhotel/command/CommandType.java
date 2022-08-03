package com.example.myhotel.command;

import com.example.myhotel.command.impl.DefaultCommand;
import com.example.myhotel.command.impl.SignInCommand;
import com.example.myhotel.command.impl.SignUpCommand;

public enum CommandType {
    SIGN_UP(new SignUpCommand()),
    SIGN_IN(new SignInCommand()),
    DEFAULT(new DefaultCommand());

    Command command;

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


//        CommandType commandType = CommandType.valueOf(commandStr.toUpperCase());
//        return commandType.command;
    }
}
