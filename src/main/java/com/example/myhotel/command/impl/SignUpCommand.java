package com.example.myhotel.command.impl;

import com.example.myhotel.command.Command;
import com.example.myhotel.controller.RequestParameter;
import com.example.myhotel.controller.Router;
import jakarta.servlet.http.HttpServletRequest;

public class SignUpCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        String email = request.getParameter(RequestParameter.EMAIL);
        String password = request.getParameter(RequestParameter.PASSWORD);


        return null;
    }

}