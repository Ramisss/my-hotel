package com.example.myhotel.command.impl;

import com.example.myhotel.command.Command;
import com.example.myhotel.controller.Router;
import com.example.myhotel.exception.CommandException;
import com.example.myhotel.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;

public class DefaultCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException, ServiceException {
        return null;
    }
}
