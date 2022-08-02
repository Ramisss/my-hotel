package com.example.myhotel.controller;

import com.example.myhotel.command.Command;
import com.example.myhotel.command.impl.SignUpCommand;

public enum CommandType {
    SIGN_UP(new SignUpCommand());

    Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public static Command define(String commandStr) {
        CommandType commandType = CommandType.valueOf(commandStr.toUpperCase());
        return commandType.command;
    }
}
