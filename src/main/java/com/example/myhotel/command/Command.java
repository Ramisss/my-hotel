package com.example.myhotel.command;

import com.example.myhotel.controller.Router;
import com.example.myhotel.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

@FunctionalInterface
public interface Command {
    Router execute(HttpServletRequest request) throws CommandException;
}
